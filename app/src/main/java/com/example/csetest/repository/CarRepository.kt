package com.example.csetest.repository

import com.example.csetest.db.CarEntity
import com.example.csetest.ui.carlist.adapter.Car

interface CarRepository {
    fun getAll(): List<Car>
    fun saveAll(list: List<Car>)
    fun getCarById(id: Int): CarEntity
    fun saveCar(car: CarEntity)
    fun getSortedList(): List<Car>
    fun sortCarsByGearbox(gearbox: String): List<Car>
}