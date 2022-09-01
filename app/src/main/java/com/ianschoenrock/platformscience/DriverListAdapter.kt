package com.ianschoenrock.platformscience

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class DriverListAdapter(
    private val drivers: List<String>,
    private val shipments: List<String>):
    RecyclerView.Adapter<DriverListAdapter.ItemViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DriverListAdapter.ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.driver_list_item, parent, false)
        return ItemViewHolder(inflater)
    }

    override fun getItemCount(): Int = drivers.count()

    override fun onBindViewHolder(holder: DriverListAdapter.ItemViewHolder, position: Int) {
        holder.setData(drivers[position], shipments[position])
    }

    inner class ItemViewHolder(val view: View): RecyclerView.ViewHolder(view){
        fun setData(driverName: String, address: String){
            val driverNameTV = view.findViewById<TextView>(R.id.driver_name_tv)
            val shipmentTv = view.findViewById<TextView>(R.id.shipment_tv)
            driverNameTV.text = driverName
            shipmentTv.text = address
            driverNameTV.setOnClickListener {
                if(shipmentTv.visibility == View.GONE) shipmentTv.visibility = View.VISIBLE
                else shipmentTv.visibility = View.GONE
            }
        }
    }


}