package com.example.csetest.ui.carlist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.csetest.R
import com.example.csetest.databinding.ItemCarBinding
import java.io.File

class CarHolder(
    private val context: Context,
    private val binding: ItemCarBinding,
    private val navigateAction: (Car) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(item: Car) {
        with(binding) {
            tvName.text = item.name
            tvEngine.text = item.engine
            tvGearbox.text = item.gearbox
            tvBody.text = item.body
            ivPicture.load(File(context.filesDir, item.picture)) {
                placeholder(R.drawable.placeholder)
                error(R.drawable.placeholder)
            }
            ivPicture.setOnClickListener {
                navigateAction(item)
            }
        }
    }

    companion object {

        fun create(
            context: Context,
            parent: ViewGroup,
            navigateAction: (Car) -> Unit
        ): CarHolder = CarHolder(
            context = context,
            binding = ItemCarBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            navigateAction = navigateAction
        )
    }
}