/*
 * Copyright 2023 ryuji koyama
 */

package com.uminari.practice.todoApp.models

import androidx.room.Entity
import io.realm.annotations.PrimaryKey
import java.util.*

@Entity
data class TodoItem(
    @PrimaryKey
    val id: Long = 0,
    val title: String = "",
    val detail: String = "",
    val createDate: Date = Date(),
    val updateDate: Date = Date(),
    val isDone: Boolean = false,
)