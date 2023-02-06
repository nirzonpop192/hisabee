package com.faisal.hiasbe.data.local

import androidx.room.*
import com.faisal.hiasbe.data.model.Item


@Dao
interface TodoDao {
    @Query("SELECT * FROM tbTodo")
    fun getToDoList() : List<Item>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(repositoryItem: Item) : Long

    @Delete
    suspend fun delete(repositoryItem: Item)

    @Query("DELETE FROM tbTodo")
    suspend fun deleteAll()
}