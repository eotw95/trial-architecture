/*
 * Copyright 2023 ryuji koyama
 */

package com.uminari.practice.todoApp

import android.util.Log
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
        Log.d(TAG, "onCreateViewHolder parent=$parent viewType=$viewType")
        setOnItemClickListener(listener) // 必要ないかも
        setOnCheckBoxClickListener(checkBoxListener) // 必要ないかも
        val inflater = LayoutInflater.from(parent.context)
        val binding = TextItemViewBinding.inflate(inflater, parent, false)
        return TodoItemViewHolder(binding, listener, longListener, checkBoxListener)
    }

    override fun onBindViewHolder(holder: TodoItemViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder holder=$holder position=$position")
        val todoItem: TodoItem? = getItem(position)
        if (todoItem != null) {
            holder.bindTo(todoItem, position)
        }
    }

    // 必要なさそう
    override fun getItemId(position: Int): Long {
        Log.d(TAG, "getItemId position=$position")
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
            Log.d(TAG, "bindTo todoItem=$todoItem position=$position")
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
        Log.d(TAG, "setOnItemClickListener listener=$listener")
        this.listener = listener
    }
    fun setOnLongItemClickListener(longListener: OnLongItemClickListener) {
        Log.d(TAG, "setOnLongItemClickListener longListener=$longListener")
        this.longListener = longListener
    }
    fun setOnCheckBoxClickListener(checkBoxListener: OnCheckBoxClickListener) {
        Log.d(TAG, "setOnCheckBoxClickListener checkBoxListener=$checkBoxListener")
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