package com.example.lijinduo.mydemo.kotlin

import android.util.Log

import java.util.Scanner

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2018/1/10
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
class Kotlin {

    fun main() {
        val fruits = listOf("banana", "avocado", "apple", "kiwi")
        fruits
                .filter { it.startsWith("a") }
                .sortedBy { it }
                .map { it.toUpperCase() }
                .forEach {
                    Log.e("wtf",it)
                    println(it) }
        }
}
