/*
 * Copyright 2023 ryuji koyama
 */

package com.uminari.practice.todoApp.viewModels

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.uminari.practice.todoApp.utils.Event

/**
 * Fragmentの生成
 * 画面遷移の管理
 */
class MainActivityViewModel: ViewModel() {
    companion object {
        private const val TAG = "MainActivityViewModel"
    }

    val navigateToFragment: LiveData<Event<FragmentNavigationRequest>>
    get() = _navigateToFragment
    private val _navigateToFragment = MutableLiveData<Event<FragmentNavigationRequest>>()

    fun showFragment(fragment: Fragment, backStack: Boolean = true, tag: String? = null) {
        _navigateToFragment.value = Event(FragmentNavigationRequest(fragment, backStack, tag))
    }
}

data class FragmentNavigationRequest(
    val fragment: Fragment,
    val backStack: Boolean = false,
    val tag: String? = null
)