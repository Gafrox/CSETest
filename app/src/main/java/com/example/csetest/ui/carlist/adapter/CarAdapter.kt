package com.example.csetest.ui.carlist.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class CarAdapter(
    private val context: Context,
    private val navigateAction: (Car) -> Unit,
    private val editAction: (Car) -> Unit
) : ListAdapter<Car, CarHolder>(CarDiff) {

    private val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
        0,
        ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
    ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.adapterPosition
            val car = getItem(position)
            editAction(car)
        }
    }

    private val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CarHolder.create(
        context = context,
        parent = parent,
        navigateAction = navigateAction
    )

    override fun onBindViewHolder(holder: CarHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }
}