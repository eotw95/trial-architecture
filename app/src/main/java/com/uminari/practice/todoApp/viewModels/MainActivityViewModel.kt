/*
 * Copyright 2023 ryuji koyama
 */

package com.uminari.practice.todoApp.viewModels

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.hadilq.liveevent.LiveEvent
import com.uminari.practice.todoApp.models.TodoItem
import com.uminari.practice.todoApp.views.TodoItemFragment

/**
 * Fragmentの生成
 * 画面遷移の管理
 */
class MainActivityViewModel: ViewModel() {
    companion object {
        private const val TAG = "MainActivityViewModel"
    }

    var selectedItem: TodoItem? = null
    private val _navigateToFragment = LiveEvent<FragmentNavigationRequest>()
    val navigateToFragment: LiveData<FragmentNavigationRequest> = _navigateToFragment

    fun showTodoItemDetail() {
        Log.d(TAG, "showTodoItemDetail")
        selectedItem = null
        showFragment(TodoItemFragment.newInstance())
    }

    fun todoItemClicked(todoItem: TodoItem) {
        Log.d(TAG, "todoItemClicked todoItem=$todoItem")
        selectedItem = todoItem
        showFragment(TodoItemFragment.newInstance())
    }

    fun showFragment(fragment: Fragment, backStack: Boolean = true, tag: String? = null) {
        Log.d(TAG, "showFragment fragment=$fragment, backStack=$backStack tag=$tag")
        _navigateToFragment.value = FragmentNavigationRequest(fragment, backStack, tag)
    }
}

data class FragmentNavigationRequest(
    val fragment: Fragment,
    val backStack: Boolean = false,
    val tag: String? = null
)