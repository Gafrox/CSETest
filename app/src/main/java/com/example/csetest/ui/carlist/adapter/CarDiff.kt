package com.example.csetest.ui.carlist.adapter

import androidx.recyclerview.widget.DiffUtil

object CarDiff : DiffUtil.ItemCallback<Car>() {
    override fun areItemsTheSame(
        oldItem: Car,
        newItem: Car
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: Car,
        newItem: Car
    ): Boolean = oldItem == newItem
}