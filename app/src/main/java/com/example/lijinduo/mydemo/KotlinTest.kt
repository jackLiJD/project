package com.example.lijinduo.mydemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class KotlinTest : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_test)

    }
    //sampleStart
    fun getStringLength(obj: Any): Int? {
        if (obj is String) {
            // 在这个分支中, `obj` 的类型会被自动转换为 `String`
            return obj.length
        }

        // 在类型检查所影响的分支之外, `obj` 的类型仍然是 `Any`
        return null
    }
//sampleEnd


    fun main(args: Array<String>) {
        fun printLength(obj: Any) {
            println("'$obj' string length is ${getStringLength(obj) ?: "... err, not a string"} ")
        }
        printLength("Incomprehensibilities")
        printLength(1000)
        printLength(listOf(Any()))
    }










}
