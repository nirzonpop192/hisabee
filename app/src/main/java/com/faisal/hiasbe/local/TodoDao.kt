package com.faisal.hiasbe.local

import androidx.room.*
import com.faisal.hiasbe.model.Item


@Dao
interface TodoDao {
    @Query("SELECT * FROM tbTodo")
    fun getRepository() : List<Item>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(repositoryItem: Item) : Long

    @Delete
    suspend fun delete(repositoryItem: Item)

    @Query("DELETE FROM tbTodo")
    suspend fun deleteAll()
}