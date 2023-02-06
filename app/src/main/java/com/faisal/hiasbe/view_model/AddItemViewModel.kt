package com.faisal.hiasbe.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faisal.hiasbe.data.model.Item
import com.faisal.hiasbe.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddItemViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel() {


    fun addToDoItem(item: Item) {
        viewModelScope.launch {
            repository.insertTodo(item)
        }

    }


}
