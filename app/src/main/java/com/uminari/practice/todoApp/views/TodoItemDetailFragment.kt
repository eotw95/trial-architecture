/*
 * Copyright 2023 ryuji koyama
 */

package com.uminari.practice.todoApp.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.uminari.practice.R
import com.uminari.practice.todoApp.viewModels.MainActivityViewModel
import com.uminari.practice.todoApp.viewModels.TodoListFragmentViewModel

class TodoItemDetailFragment: Fragment() {
    companion object {
        private const val TAG = "TodoItemDetailFragment"
    }

    private lateinit var todoItemDetailFragment: TodoItemDetailFragment
    private val mainActivityViewModel: MainActivityViewModel by activityViewModels()
    private val todoItemDetailFragmentViewModel: TodoListFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.todo_item_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Not yet implemented.
    }
}