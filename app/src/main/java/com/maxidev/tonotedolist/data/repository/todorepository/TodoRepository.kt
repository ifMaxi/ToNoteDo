package com.maxidev.tonotedolist.data.repository.todorepository

import com.maxidev.tonotedolist.data.db.entities.ToDoEntity
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    fun getAll(): Flow<List<ToDoEntity>>

    suspend fun upsertTodo(todo: ToDoEntity)

    suspend fun deleteTodo(todo: ToDoEntity)

    suspend fun deleteAll()
}