/*
 * Copyright 2023 ryuji koyama
 */

package com.uminari.practice.todoApp.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class TodoItem: RealmObject() {
    @PrimaryKey
    var id: Long = 0
    var title = ""
    var detail = ""
    var createDate = Date()
    var updateDate = Date()
    var isDone = false

}