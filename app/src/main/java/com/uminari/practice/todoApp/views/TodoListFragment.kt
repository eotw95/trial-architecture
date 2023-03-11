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
import com.uminari.practice.databinding.TodoListFragmentBinding
import com.uminari.practice.todoApp.MainActivity
import com.uminari.practice.todoApp.viewModels.MainActivityViewModel
import com.uminari.practice.todoApp.viewModels.TodoListFragmentViewModel

class TodoListFragment: Fragment() {
    companion object {
        private const val TAG = "TodoListFragment"
        fun newInstance() = TodoListFragment()
    }

    private lateinit var binding: TodoListFragmentBinding
    private val mainActivityViewModel: MainActivityViewModel by activityViewModels()
    private val TodoListFragmentViewModel: TodoListFragmentViewModel by viewModels()

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
        val view = inflater.inflate(R.layout.todo_list_fragment, container, false)
        binding = TodoListFragmentBinding.bind(view).apply {
            viewModel = TodoListFragmentViewModel
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
        super.onActivityCreated(savedInstanceState)

        //TODO: bind todo item adapter


    }
}