package com.example.sampleapp.util

class Product(val name: String, val price: Double, val quantity: Int) {


    fun calculateTotalCost(): Double {
        return price * quantity
    }
    fun displayInfo(): String
    {
        return "Property Name: $name\nPrice: $price\nQuantity: $quantity"
    }
}
