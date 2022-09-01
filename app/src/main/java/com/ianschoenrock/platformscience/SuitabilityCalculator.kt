package com.ianschoenrock.platformscience

import java.util.*
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class SuitabilityCalculator @Inject constructor() {

    private val vowels = arrayOf('a','e', 'i','o','u','y')
    private val numbers = arrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')

    fun getSuitableRoutes(drivers: List<String>, shipments: List<String>): DriverShipmentResponse{
        val addedDrivers = ArrayList<String>()
        val addedShipments = ArrayList<String>()
        val mutableDrivers = ArrayList(drivers)
        shipments.forEach{ shipment ->
            var scores = HashMap<String, Double>()
            if(shipment.count() % 2 == 0){
                scores.putAll(getEvenShipmentSS(mutableDrivers))
            } else scores.putAll(getOddShipmentSS(mutableDrivers))
            val formattedStreetName = formatStreetName(shipment)
            scores = getFactors(scores, formattedStreetName)

            val bestScore = Collections.max(scores.values)
            val bestDriver = scores.filterValues { it == bestScore }.keys.first()

            addedDrivers.add(bestDriver)
            addedShipments.add(shipment)
            mutableDrivers.remove(bestDriver)
        }
        return DriverShipmentResponse( addedShipments, addedDrivers)
    }

    private fun getEvenShipmentSS(drivers: List<String>): HashMap<String, Double>{
        val scores = HashMap<String, Double>()
        drivers.forEach{ driver ->
            var score = 0.0
            var vowelCount = 0
            driver.forEach { letter ->
                if(vowels.contains(letter)){
                    vowelCount += 1
                }
            }
            score = vowelCount * 1.5
            scores[driver] = score
        }
        return scores
    }

    private fun getOddShipmentSS(drivers: List<String>): HashMap<String, Double>{
        val scores = HashMap<String, Double>()
        drivers.forEach{ driver ->
            var score = 0.0
            driver.forEach { letter ->
                if(!vowels.contains(letter)){
                    score += 1
                }
            }
            scores[driver] = score
        }
        return scores
    }

    private fun getFactors(oldScores: HashMap<String, Double>, streetName: String): HashMap<String, Double>{
        val scores = HashMap<String, Double>()
        val formattedDrivers = formatDrivers(oldScores.keys.toList())
        var letterIndex = 1
        var driverIndex = 0
        oldScores.forEach{(driver, driverScore): Map.Entry<String, Double> ->
            while (letterIndex <= formattedDrivers[driverIndex].count() && letterIndex <= streetName.count()) {
                if (streetName.count()  % letterIndex == 0 && streetName.count() % letterIndex == 0 && letterIndex != 1) {
                    scores[driver] = driverScore + (driverScore/2)
                } else{
                    scores[driver] = driverScore
                }
                letterIndex++
            }
            letterIndex = 1
            driverIndex ++
        }
        return scores
    }

    private fun formatStreetName(shipment: String): String{
        var streetName = ""
        val shipmentWithoutSpaces = shipment.filter { !it.isWhitespace() }
        shipmentWithoutSpaces.lowercase(Locale.getDefault()).forEach { letter->
            var count = 0
            numbers.forEach{ number ->
                if(letter == number ){
                    count++
                }
            }
            if(count == 0){
                streetName += letter
            }
        }
        streetName = streetName.removeSuffix("apt.")
        streetName = streetName.removeSuffix("suite")
        return streetName
    }

    private fun formatDrivers(drivers: List<String>): ArrayList<String>{
        val formattedDrivers = ArrayList<String>()
        drivers.forEach{ driver ->
            val formattedDriver = driver.filter { letter ->  !letter.isWhitespace() }
            formattedDrivers.add(formattedDriver)
        }
        return formattedDrivers
    }
}