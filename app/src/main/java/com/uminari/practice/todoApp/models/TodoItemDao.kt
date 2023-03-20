/*
 * Copyright 2023 ryuji koyama
 */

package com.uminari.practice.todoApp.models

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

@Dao
interface TodoItemDao {
    @Insert
    fun insertTodoItem(todoItem: TodoItem)

    @Update
    fun updateTodoItem(todoItem: TodoItem)

    @Delete
    fun deleteTodoItem(todoItem: TodoItem)
}