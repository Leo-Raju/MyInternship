package com.example.sampleapp.util

import org.junit.Test

class UtilTest {
    /*@Test
    fun positive() {
        val util = Util()
        util.checkPositive(-23)
    }
    @Test
    fun negative() {
        val util = Util()
        util.checkNegative(23)
    }
    @Test
    fun zero() {
        val util = Util()
        util.checkZero(1)
    }*/
    @Test
    fun even() {
        val util = Util()
        val num1 = 6
        val num2 = 7
        var isEven = util.checkEven(num1)
        println("$num1 $isEven")
        isEven = util.checkEven(num2)
        println("$num2 $isEven")
    }
    @Test
    fun reverses() {
        val util = Util()
        val reversedString = util.reverse("GadgEon")
        println(reversedString)
    }
    @Test
    fun checkNum() {
        val util = Util()
        println(util.checkNumber(10))
        println(util.checkNumber(-5))
        println(util.checkNumber(0))
    }
}