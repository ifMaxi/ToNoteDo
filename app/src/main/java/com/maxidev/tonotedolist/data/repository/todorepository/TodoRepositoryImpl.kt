package com.maxidev.tonotedolist.data.repository.todorepository

import com.maxidev.tonotedolist.data.db.dao.ToDoDao
import com.maxidev.tonotedolist.data.db.entities.ToDoEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TodoRepositoryImpl @Inject constructor(
    private val dao: ToDoDao
): TodoRepository {
    override fun getAll(): Flow<List<ToDoEntity>> {
        return dao.getAllTodo()
    }

    override suspend fun upsertTodo(todo: ToDoEntity) {
        return dao.insertTodo(todo)
    }

    override suspend fun deleteTodo(todo: ToDoEntity) {
        return dao.deleteTodo(todo)
    }

    override suspend fun deleteAll() {
        return dao.deleteAllTodo()
    }
}