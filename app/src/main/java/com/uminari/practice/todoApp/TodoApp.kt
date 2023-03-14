package com.uminari.practice.todoApp

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import com.uminari.practice.todoApp.utils.RealmMigration
import io.realm.Realm
import io.realm.RealmConfiguration

class TodoApp: Application() {
    companion object {
        private const val TAG = "TodoApp"
    }

    @SuppressLint("LongLogTag")
    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "called onCreate")
        realmMigration()
    }

    private fun realmMigration() {
        Realm.init(this)
        val realmConfig = RealmConfiguration.Builder()
            .schemaVersion(1L)
            .migration(RealmMigration())
            .build()
        Realm.setDefaultConfiguration(realmConfig)
    }
}