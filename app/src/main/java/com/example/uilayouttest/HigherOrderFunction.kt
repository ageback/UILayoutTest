package com.example.uilayouttest

import java.lang.StringBuilder
import kotlin.math.min

inline fun num1AndNum2(num1: Int, num2: Int, operation: (Int, Int) -> Int) : Int {
    return operation(num1, num2)
}

fun plus(num1: Int, num2: Int): Int
{
    return num1 + num2
}

fun minus(num1: Int, num2: Int): Int
{
    return num1 - num2
}

/**
 * StringBuilder 扩展函数：build
 * 参数为 函数类型参数
 */
fun StringBuilder.build(block: StringBuilder.() -> Unit): StringBuilder {
    block()
    return this
}

fun main()
{
    val num1 = 100
    val num2 = 80
//    val result1 = num1AndNum2(num1, num2, ::plus)
//    val result2 = num1AndNum2(num1, num2, ::minus)
    val result1 = num1AndNum2(num1, num2) {n1,n2 -> n1 + n2}
    val result2 = num1AndNum2(num1, num2) {n1,n2 -> n1 - n2}
    println("result1 is $result1")
    println("result2 is $result2")


    val list = listOf<String>("Apple", "Orange", "Banana", "Pear", "Grape")
    val result = StringBuilder().build {
        append("Start eating fruits.\n")
        for(fruit in list) {
            append(fruit).append("\n")
        }
        append("Ate all fruits.")
    }
    println(result.toString())
}