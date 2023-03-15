/*
 * Copyright 2023 ryuji koyama
 */

package com.uminari.practice.todoApp.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.uminari.practice.R
import com.uminari.practice.databinding.TodoListFragmentBinding
import com.uminari.practice.todoApp.TodoItemAdapter
import com.uminari.practice.todoApp.models.TodoItem
import com.uminari.practice.todoApp.viewModels.MainActivityViewModel
import com.uminari.practice.todoApp.viewModels.TodoListFragmentViewModel

class TodoListFragment: Fragment() {
    companion object {
        private const val TAG = "TodoListFragment"
        fun newInstance() = TodoListFragment()
    }

    private lateinit var binding: TodoListFragmentBinding
    private val mainActivityViewModel: MainActivityViewModel by activityViewModels()
    private val todoListFragmentViewModel: TodoListFragmentViewModel by viewModels()

    /**
     * onCreateView
     *
     * show fragment
     * initialize data binding
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(
            TAG,
            "onCreateView inflater=$inflater container=$container" +
                    " savedInstanceState=$savedInstanceState"
        )
        val view = inflater.inflate(R.layout.todo_list_fragment, container, false)
        binding = TodoListFragmentBinding.bind(view).apply {
            viewModel = todoListFragmentViewModel
        }
        binding.lifecycleOwner = this.viewLifecycleOwner
        return view
    }

    /**
     * onActivityCreated
     *
     * set binding
     * set listener
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.d(TAG, "onActivityCreated savedInstanceState=$savedInstanceState")
        super.onActivityCreated(savedInstanceState)
        val adapter = TodoItemAdapter(todoListFragmentViewModel)
        binding.list.adapter = adapter

        adapter.apply {
            setOnItemClickListener(
                object : TodoItemAdapter.OnItemClickListener {
                    override fun onItemClickListener(todoItem: TodoItem, position: Int) {
                        Log.d(TAG, "onItemClickListener todoItem=$todoItem position=$position")
                        mainActivityViewModel.todoItemClicked(todoItem)
                    }
                }
            )
            setOnLongItemClickListener(
                object : TodoItemAdapter.OnLongItemClickListener {
                    override fun onLongItemClickListener(todoItem: TodoItem, position: Int): Boolean {
                        Log.d(TAG, "onLongItemClickListener todoItem=$todoItem position=$position")
                        todoListFragmentViewModel.deleteTodoItem(todoItem)
                        return true
                    }
                }
            )
            setOnCheckBoxClickListener(
                object : TodoItemAdapter.OnCheckBoxClickListener {
                    override fun onCheckBoxClickListener(todoItem: TodoItem, position: Int) {
                        Log.d(TAG, "onCheckBoxClickListener todoItem=$todoItem position=$position")
                        todoListFragmentViewModel.isDoneStateChange(todoItem.id)
                        todoListFragmentViewModel.updateTodoList()
                    }
                }
            )
        }

        binding.floatingActionButton.setOnClickListener {
            mainActivityViewModel.showTodoItemDetail()
        }
        todoListFragmentViewModel.updateTodoList()
    }
}