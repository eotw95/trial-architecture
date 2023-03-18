package com.uminari.practice.todoApp

import android.util.Log
import com.uminari.practice.todoApp.models.TodoItem
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import java.util.*

class TodoItemRepository {
    companion object {
        private const val TAG = "TodoItemRepository"
    }

    private val realm = Realm.getDefaultInstance()

    fun insert(newTitle: String, newDetail: String?) {
        Log.d(TAG, "createTodoItem newTitle=$newTitle newDetail=$newDetail")
        realm.executeTransactionAsync {db ->
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

    fun update(id: Long, newTitle: String, newDetail: String?) {
        Log.d(TAG, "updateTodoItem id=$id newTitle=$newTitle newDetail=$newDetail")
        realm.executeTransactionAsync {db ->
            val todoItem = db.where<TodoItem>().equalTo("id", id).findFirst()
            todoItem?.apply {
                title = newTitle
                detail = newDetail ?: ""
                createDate = Date()
            }
        }
    }
}