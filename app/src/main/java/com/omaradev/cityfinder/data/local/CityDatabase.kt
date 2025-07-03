package com.omaradev.cityfinder.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.omaradev.cityfinder.data.local.entity.CityEntity

@Database(
    entities = [CityEntity::class],
    version = 1,
    exportSchema = false
)
abstract class CityDatabase : RoomDatabase() {
    abstract fun cityDao(): CityDao
}
