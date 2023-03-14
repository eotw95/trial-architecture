package com.uminari.practice.todoApp.utils

import io.realm.DynamicRealm
import io.realm.RealmMigration

class RealmMigration: RealmMigration {
    override fun migrate(realm: DynamicRealm, oldVersion: Long, newVersion: Long) {
        val realmSchema = realm.schema
        var oldVersion = oldVersion
        if (oldVersion == 0L) {
            realmSchema.get("TodoItem")!!
                .removeField("isDelete")
            oldVersion++
        }
    }
}