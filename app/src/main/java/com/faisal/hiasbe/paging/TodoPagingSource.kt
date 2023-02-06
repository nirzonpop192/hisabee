package com.faisal.hiasbe.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.faisal.hiasbe.data.local.TodoDao
import com.faisal.hiasbe.data.model.Item


class TodoPagingSource(private  val doa: TodoDao) : PagingSource<Int, Item>(){

    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
        if(state.anchorPosition!=null)
        {
            val  anchorPage= state.closestPageToPosition(state.anchorPosition!!)
            if (anchorPage?.prevKey!=null)
                return anchorPage.prevKey!!.plus(1)
            else if (anchorPage?.nextKey!=null)
                return anchorPage.nextKey!!.minus(1)
            else return null

        }else
            return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {

        val pagePosition = params.key ?: 1
        // on line mode


            val dataSet = doa.getToDoList()
            Log.e("dim",doa.getToDoList().size.toString())
            return LoadResult.Page(
                data = dataSet,
                prevKey = if (pagePosition == 1) null else pagePosition - 1,
                nextKey = if (pagePosition == doa.getToDoList().size) null else pagePosition + 1
            )



    }
}