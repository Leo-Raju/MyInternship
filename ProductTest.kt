package com.example.sampleapp.util

import org.junit.Test

class ProductTest {
    @Test
    fun productinfo() {
        val product =  Product("Laptop", 1200.0, 2)
        println("Product Information:")
        val productInfo = product.displayInfo()
        println(productInfo)
        val totalCost = product.calculateTotalCost()
        println("Total cost = $totalCost")
    }
}