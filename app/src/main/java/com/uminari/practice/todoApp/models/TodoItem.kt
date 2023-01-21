package com.uminari.practice.todoApp.models

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import java.util.*

class TodoItem: RealmObject {
    @PrimaryKey
    var id: Long = 0
    var name = ""
    var detail = ""
    var createDate = Date()
    var updateDate = Date()
    var isDone = false

}