package com.faisal.hiasbe.data.local

import androidx.room.*
import com.faisal.hiasbe.data.model.Item


@Dao
interface TodoDao {
    @Query("SELECT * FROM tbTodo")
    fun getToDoList() : List<Item>

    @Query("SELECT COUNT(*) FROM tbTodo")
    fun getCount() : Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(repositoryItem: Item) : Long

    @Update
    suspend fun update(repositoryItem: Item) : Int

    @Delete
    suspend fun delete(repositoryItem: Item)

    @Query("DELETE FROM tbTodo")
    suspend fun deleteAll()
}