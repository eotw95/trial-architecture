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
    val id: Int = 0,
    var title: String = "",
    var detail: String = "",
    val createDate: Date = Date(),
    var updateDate: Date = Date(),
    var isDone: Boolean = false,
)