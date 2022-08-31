package com.ianschoenrock.platformscience

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ianschoenrock.platformscienceimport.DataServiceFromFiles
import java.io.IOException

class DriverFragment : Fragment() {

    private lateinit var driverRV:RecyclerView
    private val driverListAdapter = DriverListAdapter()
    private val suitabilityCalculator = SuitabilityCalculator()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_driver, container, false).apply{
            driverRV = findViewById(R.id.driver_rv)
            driverRV.adapter = driverListAdapter
            driverRV.layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fileService = DataServiceFromFiles(requireActivity().applicationContext, suitabilityCalculator)
        val response = fileService.getDriverShipment() //arrayListOf("One", "Two", "Three")
        fileService.getSuitableRoutes(response.drivers, response.shipments)
        driverListAdapter.submitList(response.drivers)
    }

}