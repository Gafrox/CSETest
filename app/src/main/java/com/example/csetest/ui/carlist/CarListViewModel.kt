package com.example.csetest.ui.carlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.csetest.App
import com.example.csetest.navigation.Screens
import com.example.csetest.ui.carlist.adapter.Car
import com.example.csetest.ui.repository

class CarListViewModel : ViewModel() {

    private val router = App.INSTANCE.router

    private val _cars = MutableLiveData<List<Car>>()
    val cars: LiveData<List<Car>> = _cars

    init {
        _cars.value = repository.getAll()
    }

    fun onAddClicked() {
        router.navigateTo(Screens.addCar())
    }

    fun onFilterClicked() {
        _cars.value = repository.sortCarsByGearbox("Robotic")
    }

    fun onSortClicked() {
        _cars.value = repository.getSortedList()
    }

    fun updateList() {
        _cars.value = repository.getAll()
    }
}