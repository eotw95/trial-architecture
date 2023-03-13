/*
 * Copyright 2023 ryuji koyama
 */

package com.uminari.practice.todoApp.extension

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun Date.toString(pattern: String = "yyyy/MM/dd"): String? {
    return SimpleDateFormat(pattern).format(this)
}