/*
 * Copyright 2023 ryuji koyama
 */

package com.uminari.practice.todoApp.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.uminari.practice.todoApp.models.TodoItem
import io.realm.kotlin.where
import io.realm.Realm
import io.realm.kotlin.createObject
import java.util.*

class TodoItemFragmentViewModel: ViewModel() {
    companion object {
        private const val TAG = "TodoItemFragmentVM"
    }

    private val realm = Realm.getDefaultInstance()

    override fun onCleared() {
        Log.d(TAG, "onCleared")
        super.onCleared()
        realm.close()
    }

    fun createTodoItem(newTitle: String, newDetail: String?) {
        Log.d(TAG, "createTodoItem newTitle=$newTitle newDetail=$newDetail")
        realm.executeTransaction {db ->
            val maxId = db.where<TodoItem>().max("id")
            val nextId = (maxId?.toLong() ?: 0L) + 1
            val todoItem = db.createObject<TodoItem>(nextId)
            todoItem.apply {
                title = newTitle
                detail = newDetail ?: ""
                createDate = Date()
            }
        }
    }

    fun updateTodoIetem(id: Long, newTitle: String, newDetail: String?) {
        Log.d(TAG, "updateTodoItem id=$id newTitle=$newTitle newDetail=$newDetail")
        realm.executeTransaction {db ->
            val todoItem = db.where<TodoItem>().equalTo("id", id).findFirst()
            todoItem?.apply {
                title = newTitle
                detail = newDetail ?: ""
                createDate = Date()
            }
        }
    }
}