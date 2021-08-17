package com.qw.kotlin

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        println(stringLengthFunc("123456"))
    }

    /**
     * 匿名函数写法
     * stringLengthFunc 函数变量
     * (String) -> Int     String:参数类型  -> Int 函数返回类型
     *
     * { input ->          input为参数变量名  -> 代码块 需要返回Int类型值
     *
     */
    val stringLengthFunc: (String) -> Int = { input ->
        input.length
    }

}