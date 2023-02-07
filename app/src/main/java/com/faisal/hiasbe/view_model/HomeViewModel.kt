package com.faisal.hiasbe.view_model

import android.util.Log
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
     var todoList: MutableLiveData<List<Item>> =MutableLiveData()
     var counted: MutableLiveData<Int> =MutableLiveData()


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
    fun count(){
        viewModelScope.launch{
            counted.value=repository.getCount()

        }

    }
    fun load(){
        viewModelScope.launch {
           // pagingDataList=repository.loadTodoList().cachedIn(viewModelScope)
//          var  dim:List<Item> =repository.getData()
//            Log.e("dim","dim list "+dim.size)
            todoList.value=repository.getData()


        }
    }

    fun updateToDoItem(item: Item) {
        viewModelScope.launch {
            repository.update(item)
        }

    }

    /**
     * load method invoke the pager
     */
    fun loadData() {
        isLoading.value=true
        viewModelScope.launch {
            pagingDataList=repository.loadTodoList().cachedIn(viewModelScope)
            todoList.value=repository.getData()
//            todoList.value=repository.getData()

        }

    }


}