package com.ianschoenrock.platformscienceimport

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ianschoenrock.platformscience.DriverShipmentResponse
import com.ianschoenrock.platformscience.SuitabilityCalculator
import java.io.IOException

class DataServiceFromFiles(context: Context, private val suitabilityCalculator: SuitabilityCalculator) {
    private val driverShipmentDataString = getJsonDataFromAsset(context)
    private val driverShipmentType = object : TypeToken<DriverShipmentResponse>(){}.type

    private fun getJsonDataFromAsset(context: Context): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open("data.json").bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

    fun getDriverShipment(): DriverShipmentResponse{
        val gson = Gson()
        return gson.fromJson(driverShipmentDataString, driverShipmentType)
    }


}