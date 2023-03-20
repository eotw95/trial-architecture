package com.uminari.practice.todoApp

import com.uminari.practice.todoApp.models.TodoItem
import com.uminari.practice.todoApp.models.TodoItemDao

class TodoItemRepository(private val todoItemDao: TodoItemDao) {
    companion object {
        private const val TAG = "TodoItemRepository"
    }

    suspend fun insert(todoItem: TodoItem) {
        todoItemDao.insertTodoItem(todoItem)
    }

    suspend fun update(todoItem: TodoItem) {
        todoItemDao.updateTodoItem(todoItem)
    }

    suspend fun delete(todoItem: TodoItem) {
        todoItemDao.deleteTodoItem(todoItem)
    }
}