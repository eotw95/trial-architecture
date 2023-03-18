/*
 * Copyright 2023 ryuji koyama
 */

package com.uminari.practice.todoApp.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.uminari.practice.todoApp.TodoItemRepository
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
    private val todoItemRepository = TodoItemRepository()

    override fun onCleared() {
        Log.d(TAG, "onCleared")
        super.onCleared()
        realm.close()
    }

    fun addTodoItem(newTitle: String, newDetail: String?) {
        Log.d(TAG, "createTodoItem newTitle=$newTitle newDetail=$newDetail")
        todoItemRepository.insert(newTitle, newDetail)
    }

    fun updateTodoItem(id: Long, newTitle: String, newDetail: String?) {
        Log.d(TAG, "updateTodoItem id=$id newTitle=$newTitle newDetail=$newDetail")
        todoItemRepository.update(id, newTitle, newDetail)
    }
}