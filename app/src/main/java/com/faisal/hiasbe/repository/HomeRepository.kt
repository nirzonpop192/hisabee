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
        config = PagingConfig(pageSize = 3, maxSize = 9),
        pagingSourceFactory = { TodoPagingSource(dao) }

    ).liveData

    suspend fun insertTodo(repository: Item){
        val  row=dao.insert(repository)
        Log.e(TAG, "row effected $row")
    }




    fun getData() : List<Item> {
        return dao.getToDoList()
    }

    fun getCount():Int{
        return dao.getCount()
    }

    suspend fun deleteAllRepositories (){
        dao.deleteAll()
    }



}