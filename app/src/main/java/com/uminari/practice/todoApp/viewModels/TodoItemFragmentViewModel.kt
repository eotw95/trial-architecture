/*
 * Copyright 2023 ryuji koyama
 */

package com.uminari.practice.todoApp.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uminari.practice.todoApp.TodoItemRepository
import com.uminari.practice.todoApp.models.AppDatabase
import com.uminari.practice.todoApp.models.TodoItem
import io.realm.kotlin.where
import io.realm.Realm
import io.realm.kotlin.createObject
import kotlinx.coroutines.launch
import java.util.*

class TodoItemFragmentViewModel(application: Application): ViewModel() {
    companion object {
        private const val TAG = "TodoItemFragmentVM"
    }

    private val todoItemRepository: TodoItemRepository

    init {
        val todoDatabase = AppDatabase.getDatabase(application)
        val todoItemDao = todoDatabase.todoItemDao()
        todoItemRepository = TodoItemRepository(todoItemDao)
    }

    override fun onCleared() {
        Log.d(TAG, "onCleared")
        super.onCleared()
    }

    fun addTodoItem(todoItem: TodoItem) {
        viewModelScope.launch {
            Log.d(TAG, "createTodoItem todoItem=$todoItem")
            todoItemRepository.insert(todoItem)
        }
    }

    fun updateTodoItem(todoItem: TodoItem) {
        viewModelScope.launch {
            Log.d(TAG, "updateTodoItem todoItem=$todoItem")
            todoItemRepository.update(todoItem)
        }
    }
}