/*
 * Copyright 2023 ryuji koyama
 */

package com.uminari.practice.todoApp.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uminari.practice.todoApp.TodoItemRepository
import com.uminari.practice.todoApp.models.AppDatabase
import com.uminari.practice.todoApp.models.TodoItem
import io.realm.OrderedRealmCollection
import io.realm.kotlin.where
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.coroutines.launch

/**
 * TodoListFragmentの表示
 * データの更新
 */
class TodoListFragmentViewModel(application: Application): ViewModel() {
    companion object {
        private const val TAG = "TodoListFragmentVM"
    }

    private lateinit var todoItems: MutableList<TodoItem>
    lateinit var title: MutableLiveData<String>
    lateinit var data: OrderedRealmCollection<TodoItem>

    private var todoItemRepository: TodoItemRepository

    init {
        val db = AppDatabase.getDatabase(application)
        val todoDao = db.todoItemDao()
        todoItemRepository = TodoItemRepository(todoDao)
        updateTodoList()
    }

    override fun onCleared() {
        Log.d(TAG, "onCleared")
        super.onCleared()
    }

    fun updateTodoList() {
        Log.d(TAG, "updateTodoList")
        realm = Realm.getDefaultInstance()
        todoItems = realm.where<TodoItem>().findAll()
        data = (todoItems as RealmResults<TodoItem>?)!!
    }

    fun isDoneStateChange(id: Long) {
        Log.d(TAG, "isDoneStateChange id=$id")
        realm.executeTransactionAsync { db ->
            val todoItem = db.where<TodoItem>().equalTo("id", id).findFirst()
            if (todoItem != null) {
                todoItem.isDone = !todoItem.isDone
            }
        }
    }

    fun deleteTodoItem(todoItem: TodoItem) {
        Log.d(TAG, "deleteTask todoItem=$todoItem")
        viewModelScope.launch {
            todoItemRepository.delete(todoItem)
        }
    }
}