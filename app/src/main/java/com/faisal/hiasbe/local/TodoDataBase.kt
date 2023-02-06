package com.faisal.hiasbe.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.faisal.hiasbe.di.ApplicationScope
import com.faisal.hiasbe.model.Item
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Provider



@Database(version = 1, entities = [Item::class])

abstract class TodoDataBase : RoomDatabase() {


    abstract fun  getTodoDao():TodoDao


    class Callback @Inject constructor(
        private val database: Provider<TodoDataBase>
        ,@ApplicationScope private val applicationScope: CoroutineScope
    ):RoomDatabase.Callback()

}