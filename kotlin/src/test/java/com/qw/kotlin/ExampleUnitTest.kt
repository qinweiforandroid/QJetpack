package com.qw.kotlin

import org.junit.Test

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
     * 函数类型的参数
     *
     * funParam:参数名称
     * (String):String 函数参数的函数参数类型 ->Int 函数参数的函数参数返回类型
     */
    fun a(funParam: (String) -> Int): Int {
        return funParam("123")
    }

    fun b(str: String): Int {
        return str.length
    }

    @Test
    fun testMethod() {
        //::b 加了两个冒号，这个函数才变成了一个对象
        a(::b)
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

    val sum: (Int, Int) -> Int = { numA, numB ->
        numA + numB
    }

    class User {
        var id: String = ""
        var name: String = ""
        override fun toString(): String {
            return "User(id='$id', name='$name')"
        }
    }
}