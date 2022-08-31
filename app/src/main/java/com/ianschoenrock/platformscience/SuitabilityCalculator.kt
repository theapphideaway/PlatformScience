package com.ianschoenrock.platformscience

import java.util.*
import kotlin.collections.ArrayList

class SuitabilityCalculator {

    private val vowels = arrayOf('a','e', 'i','o','u','y')
    private val numbers = arrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')
    private val noNoWords = arrayOf("apt.", "suite")

    //Need to calculate scores and store the best street with the driver in a hashmap
    fun getSuitableRoutes(drivers: List<String>, addresses: List<String>){
        val formattedDrivers = formatDrivers(drivers)
        addresses.forEach{ address ->
            if(address.count() % 2 == 0){
                getEvenShipmentSS(drivers)
            } else getOddShipmentSS(drivers)
            val streetName = formatStreetName(address)
            formattedDrivers.forEach { driver ->
                getFactors(driver, streetName)
            }
        }
    }

    private fun getEvenShipmentSS(drivers: List<String>){
        val scores = ArrayList<Double>()
        drivers.forEach{
            var score = 0.0
            var vowelCount = 0
            it.forEach {
                if(vowels.contains(it)){
                    vowelCount += 1
                }
            }
            score = vowelCount * 1.5
            println("$it: $score")
            scores.add(score)
        }
    }

    private fun getOddShipmentSS(drivers: List<String>){
        val scores = ArrayList<Double>()
        drivers.forEach{ driver ->
            var score = 0.0
            driver.forEach { letter ->
                if(!vowels.contains(letter)){
                    score += 1
                }
            }
            println("$driver: $score")
            scores.add(score)
        }
    }

    private fun getFactors(driver: String, streetName: String){
        val factors = ArrayList<Int>()
        var i = 1
        while (i <= driver.count() && i <= streetName.count()) {
            if (streetName.count()  % i == 0 && streetName.count() % i == 0 && i != 1) {
                factors.add(i)
            }
            i++
        }
        println(factors)
    }

    private fun formatStreetName(address: String): String{
        var streetName = ""
        val addressWithoutSpaces = address.filter { !it.isWhitespace() }
        addressWithoutSpaces.lowercase(Locale.getDefault()).forEach { letter->
            var count = 0
            println(letter)
            numbers.forEach{ number ->
                if(letter == number ){
                    count++
                }
            }
            if(count == 0){
                streetName += letter
            }
        }
        return streetName
    }

    private fun formatDrivers(drivers: List<String>): ArrayList<String>{
        val formattedDrivers = ArrayList<String>()
        drivers.forEach{ driver ->
            val formattedDriver = driver.filter { letter ->  letter.isWhitespace() }
            formattedDrivers.add(formattedDriver)
        }
        return formattedDrivers
    }



}