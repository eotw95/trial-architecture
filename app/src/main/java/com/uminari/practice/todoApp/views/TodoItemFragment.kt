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
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.uminari.practice.R
import com.uminari.practice.databinding.TodoItemFragmentBinding
import com.uminari.practice.todoApp.extension.toString
import com.uminari.practice.todoApp.models.TodoItem
import com.uminari.practice.todoApp.viewModels.MainActivityViewModel
import com.uminari.practice.todoApp.viewModels.TodoItemFragmentViewModel
import com.uminari.practice.todoApp.viewModels.TodoListFragmentViewModel

class TodoItemFragment: Fragment() {
    companion object {
        private const val TAG = "TodoItemDetailFragment"
        fun newInstance() = TodoItemFragment()
    }

    private lateinit var binding: TodoItemFragmentBinding
    private val mainActivityViewModel: MainActivityViewModel by activityViewModels()
    private val todoItemFragmentViewModel: TodoItemFragmentViewModel by viewModels()

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
        val view = inflater.inflate(R.layout.todo_item_fragment, container, false)
        binding = TodoItemFragmentBinding.bind(view).apply {
            viewmodel = todoItemFragmentViewModel
        }
        binding.lifecycleOwner = this.viewLifecycleOwner
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.d(TAG, "onActivityCreated savedInstanceState=$savedInstanceState")
        super.onActivityCreated(savedInstanceState)

        binding.buttonRight.setOnClickListener { closeFragment() }

        val selectedItem = mainActivityViewModel.selectedItem
        if (selectedItem == null) {
            showCreateNewItem()
        } else {
            showItem(selectedItem)
        }
    }

    private fun closeFragment() {
        Log.d(TAG, "closeFragment")
        val mainActivity = activity
        if (mainActivity != null) {
            mainActivity.supportFragmentManager.popBackStack()
        } else {
            mainActivityViewModel.showTodoItemDetail()
        }
    }

    private fun showCreateNewItem() {
        Log.d(TAG, "showCreateNewItem")
        binding.buttonLeft.setOnClickListener {
            todoItemFragmentViewModel.createTodoItem(
                binding.editTitle.text.toString(),
                binding.editDetail.text.toString()
            )
            closeFragment()
        }
    }

    private fun showItem(todoItem: TodoItem) {
        Log.d(TAG, "showItem todoItem=$todoItem")
        binding.editTitle.setText(todoItem.title, TextView.BufferType.EDITABLE)
        binding.editDetail.setText(todoItem.detail, TextView.BufferType.EDITABLE)
        binding.editCreate.text = todoItem.createDate.toString("yyyy/MM/dd")
        binding.createDate.isVisible = true
        binding.editCreate.isVisible = true
        if (todoItem.createDate != todoItem.updateDate) {
            binding.editUpdate.text = todoItem.updateDate.toString()
            binding.editUpdate.isVisible = true
            binding.update.isVisible = true
        }
        binding.buttonLeft.text = "更新"
        binding.buttonLeft.setOnClickListener {
            todoItemFragmentViewModel.updateTodoIetem(
                todoItem.id,
                binding.editTitle.text.toString(),
                binding.editDetail.text.toString()
            )
            closeFragment()
        }
    }
}