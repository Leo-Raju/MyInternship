package com.example.sampleapp.util

class Car(val make: String, val model: String, val year: Int) {

    var testString : String = "Test"
    fun displayInfo(): String {
        return "Car Make: $make\nCar Model: $model\nCar Year: $year"
    }
}