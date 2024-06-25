package com.example.sampleapp.util

class Util {
    /*fun checkPositive(num: Int) {
        if (num > 0)
            println("$num is positive.")
        else
            println("$num is not positive.")
    }
    fun checkNegative(num: Int) {
        if (num < 0)
            println("$num is negative.")
        else
            println("$num is not negative.")

    }*/
    fun checkZero(num: Int) {
        if (num == 0)
            println("$num is zero.")
        else
            println("$num is not zero.")
    }
    fun checkEven(number: Int) : Boolean{
        return ( number % 2 == 0 )
    }
    fun reverse(str: String) : String{
        val reversedStr = str.reversed()
        return reversedStr
    }
    fun checkNumber(num: Int): String {
            return when {
                num > 0 -> "$num is positive."
                num < 0 -> "$num is negative."
                else -> "$num is zero."
            }
    }
}