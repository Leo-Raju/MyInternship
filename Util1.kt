package com.example.sampleapp.util

class Util1 {
    fun countEvenOdd(num:Array<Int>){

        var evenCount = 0
        var oddCount = 0

        for (number in num) {
            if (number % 2 == 0) {
                evenCount++
            } else {
                oddCount++
            }
        }

        println("Number of even elements: $evenCount")
        println("Number of odd elements: $oddCount")
    }
}