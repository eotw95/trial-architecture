/*
 * Copyright 2023 ryuji koyama
 */

package com.uminari.practice.todoApp.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.uminari.practice.todoApp.models.TodoItem
import io.realm.kotlin.where
import io.realm.Realm

/**
 * TodoListFragmentの表示
 * データの更新
 */
class TodoListFragmentViewModel: ViewModel() {
    companion object {
        private const val TAG = "TodoListFragmentVM"
    }

    private lateinit var realm: Realm
    private lateinit var todoItems: MutableList<TodoItem>

    init {
        updateTodoList()
    }

    override fun onCleared() {
        Log.d(TAG, "onCleared")
        super.onCleared()
        realm.close()
    }

    private fun updateTodoList() {
        Log.d(TAG, "updateTodoList")
        realm = Realm.getDefaultInstance()
        todoItems = realm.where<TodoItem>().findAll()

    }

    private fun isDoneStateChange(id: Long) {
        Log.d(TAG, "isDoneStateChange id=$id")
        realm.executeTransaction { db ->
            val todoItem = db.where<TodoItem>().equalTo("id", id).findFirst()
            if (todoItem != null) {
                todoItem.isDone = !todoItem.isDone
            }
        }
    }

    private fun deleteTodoItem(todoItem: TodoItem) {
        Log.d(TAG, "deleteTask todoItem=$todoItem")
        realm.executeTransaction { db ->
            val todoItem = db.where<TodoItem>().equalTo("id", todoItem.id).findFirst()
            todoItem?.deleteFromRealm()
        }
    }
}