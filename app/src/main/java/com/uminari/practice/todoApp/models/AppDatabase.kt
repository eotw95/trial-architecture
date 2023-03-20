/*
 * Copyright 2023 ryuji koyama
 */

package com.uminari.practice.todoApp.models

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TodoItem::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun todoItemDao(): TodoItemDao
}