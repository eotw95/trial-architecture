/*
 * Copyright 2023 ryuji koyama
 */

package com.uminari.practice.todoApp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uminari.practice.databinding.TextItemViewBinding
import com.uminari.practice.todoApp.models.TodoItem
import com.uminari.practice.todoApp.viewModels.TodoListFragmentViewModel
import io.realm.RealmRecyclerViewAdapter

class TodoItemAdapter(viewModel: TodoListFragmentViewModel):
    RealmRecyclerViewAdapter<TodoItem, TodoItemAdapter.TodoItemViewHolder>(
    viewModel.data,
    true
) {
    companion object {
        private const val TAG = "TodoItemAdapter"
    }

    private lateinit var listener: OnItemClickListener
    private lateinit var longListener: OnLongItemClickListener
    private lateinit var checkBoxListener: OnCheckBoxClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoItemViewHolder {
        setOnItemClickListener(listener) // 必要ないかも
        setOnCheckBoxClickListener(checkBoxListener) // 必要ないかも
        val inflater = LayoutInflater.from(parent.context)
        val binding = TextItemViewBinding.inflate(inflater, parent, false)
        return TodoItemViewHolder(binding, listener, longListener, checkBoxListener)
    }

    override fun onBindViewHolder(holder: TodoItemViewHolder, position: Int) {
        val todoItem: TodoItem? = getItem(position)
        if (todoItem != null) {
            holder.bindTo(todoItem, position)
        }
    }

    // 必要なさそう
    override fun getItemId(position: Int): Long {
        return getItem(position)?.id ?: 0L
    }

    class TodoItemViewHolder(
        binding: TextItemViewBinding,
        private var listener: OnItemClickListener,
        private var longListener: OnLongItemClickListener,
        private var checkBoxListener: OnCheckBoxClickListener
    ): RecyclerView.ViewHolder(binding.root) {
        val mBinding = binding
        fun bindTo(todoItem: TodoItem, position: Int) {
            mBinding.apply {
                title.text = todoItem.title
                checkBox.apply {
                    isChecked = todoItem.isDone
                    setOnClickListener {
                        checkBoxListener.onCheckBoxClickListener(todoItem, position)
                    }
                }
                root.apply {
                    setOnClickListener { listener.onItemClickListener(todoItem, position) }
                    setOnLongClickListener {
                        longListener.onLongItemClickListener(todoItem, position)
                    }
                }
                executePendingBindings()
            }
        }
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
    fun setOnLongItemClickListener(longListener: OnLongItemClickListener) {
        this.longListener = longListener
    }
    fun setOnCheckBoxClickListener(checkBoxListener: OnCheckBoxClickListener) {
        this.checkBoxListener = checkBoxListener
    }

    interface OnItemClickListener {
        fun onItemClickListener(todoItem: TodoItem, position: Int)
    }
    interface OnLongItemClickListener {
        fun onLongItemClickListener(todoItem: TodoItem, position: Int): Boolean
    }
    interface OnCheckBoxClickListener {
        fun onCheckBoxClickListener(todoItem: TodoItem, position: Int)
    }

}