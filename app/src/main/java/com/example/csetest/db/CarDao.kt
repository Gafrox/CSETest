package com.example.csetest.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CarDao {

    @Query("SELECT * FROM cars")
    fun getAll(): List<CarEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAll(list: List<CarEntity>)

    @Query("SELECT * FROM cars WHERE id=:id")
    fun getCarById(id: Int): CarEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveCar(car: CarEntity)

    @Query("SELECT * FROM cars WHERE gearbox=:gearbox")
    fun getCarsWithGearbox(gearbox: String): List<CarEntity>
}