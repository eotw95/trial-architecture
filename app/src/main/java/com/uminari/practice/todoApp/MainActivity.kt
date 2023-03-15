/*
 * Copyright 2023 ryuji koyama
 */

package com.uminari.practice.todoApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.uminari.practice.R
import com.uminari.practice.todoApp.viewModels.MainActivityViewModel
import com.uminari.practice.todoApp.views.TodoListFragment

class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "MainActivity"
    }
    private val mainActivityViewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate savedInstanceState=$savedInstanceState")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // observe navigateToFragment
        mainActivityViewModel.navigateToFragment.observe(this, Observer {
            it.getContentIfNotHandled()?.let { fragmentNavigationRequest ->
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(
                    R.id.fragmentContainer,
                    fragmentNavigationRequest.fragment,
                    fragmentNavigationRequest.tag
                )
                if (fragmentNavigationRequest.backStack) {
                    transaction.addToBackStack(null)
                }
                transaction.commit()
            }
        })
        val fragment = TodoListFragment.newInstance()
        mainActivityViewModel.showFragment(fragment)
    }
}