package com.example.csetest.repository

import com.example.csetest.db.CarDao
import com.example.csetest.db.CarEntity
import com.example.csetest.ui.carlist.adapter.Car

class CarRepositoryImpl(private val dao: CarDao) : CarRepository {

    override fun getAll() = dao.getAll().map { it.toDto() }

    override fun saveAll(list: List<Car>) {
        val result = list.map {
            CarEntity.fromDto(it)
        }
        dao.saveAll(result)
    }

    override fun getCarById(id: Int) = dao.getCarById(id)

    override fun saveCar(car: CarEntity) = dao.saveCar(car)

    override fun getSortedList() = getAll().sortedBy { it.name }

    override fun sortCarsByGearbox(gearbox: String) =
        dao.getCarsWithGearbox(gearbox).map { it.toDto() }
}