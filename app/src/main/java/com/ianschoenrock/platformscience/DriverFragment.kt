package com.ianschoenrock.platformscience

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DriverFragment : Fragment() {
    @Inject lateinit var suitabilityCalculator: SuitabilityCalculator
    private lateinit var driverRV:RecyclerView
    private lateinit var driverListAdapter:DriverListAdapter
    private val gson = Gson()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_driver, container, false).apply{
            driverRV = findViewById(R.id.driver_rv)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val response = getFileData()
        val driversAddresses = suitabilityCalculator.getSuitableRoutes(response.drivers, response.shipments)
        driverListAdapter = DriverListAdapter(driversAddresses.drivers, driversAddresses.shipments)
        driverRV.adapter = driverListAdapter
        driverRV.layoutManager = LinearLayoutManager(context)
    }

    private fun getFileData(): DriverShipmentResponse{
        val jsonString = requireActivity().applicationContext.assets.open("data.json").bufferedReader().use { it.readText() }
        return gson.fromJson(jsonString, DriverShipmentResponse::class.java)
    }
}