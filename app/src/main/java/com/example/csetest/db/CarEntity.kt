package com.example.csetest.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.csetest.ui.carlist.adapter.Car

@Entity(tableName = "cars")
data class CarEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "engine")
    val engine: String,
    @ColumnInfo(name = "gearbox")
    val gearbox: String,
    @ColumnInfo(name = "body")
    val body: String,
    @ColumnInfo(name = "picture")
    val picture: String
) {

    fun toDto() = Car(
        id = id,
        name = name,
        engine = engine,
        gearbox = gearbox,
        body = body,
        picture = picture
    )

    companion object {
        fun fromDto(dto: Car) = CarEntity(
            id = dto.id,
            name = dto.name,
            engine = dto.engine,
            gearbox = dto.gearbox,
            body = dto.body,
            picture = dto.picture
        )
    }
}