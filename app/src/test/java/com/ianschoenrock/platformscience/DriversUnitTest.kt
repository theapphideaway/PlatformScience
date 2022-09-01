package com.ianschoenrock.platformscience

import com.google.gson.Gson

import org.junit.Assert
import org.junit.Before
import org.junit.Test


class DriversUnitTest {

    private lateinit var response: DriverShipmentResponse
    private val suitabilityCalculator = SuitabilityCalculator()
    private lateinit var driversShipments: DriverShipmentResponse
    private val gson = Gson()

    @Before
    fun setup() {
        response = gson.fromJson(jsonData, DriverShipmentResponse::class.java) //arrayListOf("One", "Two", "Three")
        driversShipments = suitabilityCalculator.getSuitableRoutes(response.drivers, response.shipments)
    }


    @Test
    fun driver_quantity_isCorrect() {
        val test = driversShipments.drivers.count()
        println(test)
        Assert.assertEquals(10, driversShipments.drivers.count())
    }

    @Test
    fun address_quantity_isCorrect() {
        val test = driversShipments.shipments.count()
        println(test)
        Assert.assertEquals(10, driversShipments.drivers.count())
    }

    @Test
    fun no_duplicate_drivers_exist(){
        val drivers = driversShipments.drivers.toMutableList()
        drivers.forEach{ driver ->
            var count = 0
            drivers.forEach { comparedDriver ->
                if(driver == comparedDriver) count++
            }
            Assert.assertFalse(count > 1)
        }
    }

    @Test
    fun no_duplicate_shipments_exist(){
        val shipments = driversShipments.shipments.toMutableList()
        shipments.forEach{ shipment ->
            var count = 0
            shipments.forEach { comparedShipment ->
                if(shipment == comparedShipment) count++
            }
            Assert.assertFalse(count > 1)
        }
    }

    val jsonData = "{\n" +
            "  \"shipments\": [\n" +
            "    \"215 Osinski Manors\",\n" +
            "    \"9856 Marvin Stravenue\",\n" +
            "    \"7127 Kathlyn Ferry\",\n" +
            "    \"987 Champlin Lake\",\n" +
            "    \"63187 Volkman Garden Suite 447\",\n" +
            "    \"75855 Dessie Lights\",\n" +
            "    \"1797 Adolf Island Apt. 744\",\n" +
            "    \"2431 Lindgren Corners\",\n" +
            "    \"8725 Aufderhar River Suite 859\",\n" +
            "    \"79035 Shanna Light Apt. 322\"\n" +
            "  ],\n" +
            "  \"drivers\": [\n" +
            "    \"Everardo Welch\",\n" +
            "    \"Orval Mayert\",\n" +
            "    \"Howard Emmeruch\",\n" +
            "    \"Izaiah Lowe\",\n" +
            "    \"Monica Hermann\",\n" +
            "    \"Ellis Wisozk\",\n" +
            "    \"Noemie Murphy\",\n" +
            "    \"Cleve Durgan\",\n" +
            "    \"Murphy Mosciski\",\n" +
            "    \"Kaiser Sose\"\n" +
            "  ]\n" +
            "}"
}