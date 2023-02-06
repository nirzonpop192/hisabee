package com.faisal.hiasbe.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.faisal.hiasbe.data.model.Item
import com.faisal.hiasbe.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel(){

    companion object{


    }
    var isLoading:MutableLiveData<Boolean> =MutableLiveData()
    lateinit var pagingDataList: LiveData<PagingData<Item>>


//    fun addRepository(repositoryItem: Item){
//        viewModelScope.launch {
//            repository.insertTodo(repositoryItem)
//        }
//
//    }



    fun deleteAllRecords(){
        viewModelScope.launch {
            repository.deleteAllRepositories()

        }
    }



    /**
     * load method invoke the pager
     */
    fun loadData() {
        isLoading.value=true
        viewModelScope.launch {
            pagingDataList=repository.loadTodoList().cachedIn(viewModelScope)

        }

    }


}