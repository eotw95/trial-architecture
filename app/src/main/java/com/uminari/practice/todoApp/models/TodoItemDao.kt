/*
 * Copyright 2023 ryuji koyama
 */

package com.uminari.practice.todoApp.models

import androidx.room.*

@Dao
interface TodoItemDao {
    @Insert
    suspend fun insertTodoItem(todoItem: TodoItem)

    @Query("SELECT * FROM Todo")
    suspend fun getAllTodos(): List<TodoItem>

    @Query("SELECT * FROM Todo where id = :id")
    suspend fun getTodo(id: Int): TodoItem

    @Update
    suspend fun updateTodoItem(todoItem: TodoItem)

    @Delete
    suspend fun deleteTodoItem(todoItem: TodoItem)
}