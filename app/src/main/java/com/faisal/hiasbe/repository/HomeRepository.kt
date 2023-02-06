package com.faisal.hiasbe.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.faisal.hiasbe.data.local.TodoDao
import com.faisal.hiasbe.data.model.Item
import com.faisal.hiasbe.paging.TodoPagingSource
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class HomeRepository @Inject constructor(

    private  val dao:TodoDao,

    ) {
    companion object{
        private const val TAG="HomeRepository"
    }



    fun loadTodoList()= Pager(
        config = PagingConfig(pageSize = 10, maxSize = 5),
        pagingSourceFactory = { TodoPagingSource(dao) }

    ).liveData

    suspend fun insertTodo(repository: Item){
        val  row=dao.insert(repository)
        Log.e(TAG, "row effected $row")
    }




    fun getRepositories() : List<Item> {
        return dao.getToDoList()
    }

    suspend fun deleteAllRepositories (){
        dao.deleteAll()
    }



}