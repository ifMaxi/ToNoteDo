package com.maxidev.tonotedolist.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.maxidev.tonotedolist.data.db.entities.ToDoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDao {
    @Query("SELECT * FROM todo")
    fun getAllTodo(): Flow<List<ToDoEntity>>

    @Query("DELETE FROM todo")
    suspend fun deleteAllTodo()

    @Upsert
    suspend fun insertTodo(grocery: ToDoEntity)

    @Delete
    suspend fun deleteTodo(grocery: ToDoEntity)
}