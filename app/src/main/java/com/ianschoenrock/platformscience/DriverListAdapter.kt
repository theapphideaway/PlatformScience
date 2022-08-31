package com.ianschoenrock.platformscience

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class DriverListAdapter: ListAdapter<String, DriverListAdapter.ItemViewHolder>(DIFF_CALLBACK){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DriverListAdapter.ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.driver_list_item, parent, false)
        return ItemViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: DriverListAdapter.ItemViewHolder, position: Int) {
        holder.setData(getItem(position))
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(
                oldItem: String,
                newItem: String
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: String,
                newItem: String
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class ItemViewHolder(val view: View): RecyclerView.ViewHolder(view){
        fun setData(driverName: String){
            val driverNameTV = view.findViewById<TextView>(R.id.driver_name_tv)
            val shipmentAddress = view.findViewById<TextView>(R.id.shipment_address_tv)
            driverNameTV.text = driverName
            driverNameTV.setOnClickListener {
                if(shipmentAddress.visibility == View.GONE) shipmentAddress.visibility = View.VISIBLE
                else shipmentAddress.visibility = View.GONE
            }
        }
    }
}