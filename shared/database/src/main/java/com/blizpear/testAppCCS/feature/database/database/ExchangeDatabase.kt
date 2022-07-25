package com.blizpear.testAppCCS.feature.database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.blizpear.testAppCCS.feature.database.dao.ExchangeDao
import com.blizpear.testAppCCS.feature.database.dao.FavoritesDao
import com.blizpear.testAppCCS.feature.database.entities.ExchangeEntity
import com.blizpear.testAppCCS.feature.database.entities.FavoritesEntity

@Database(
	entities = [
		FavoritesEntity::class,
		ExchangeEntity::class
	],
	version = 1,
	exportSchema = false
)
abstract class ExchangeDatabase : RoomDatabase() {

	companion object {

		const val DATABASE_NAME = "exchange_database"
	}

	abstract fun favoriteDao(): FavoritesDao
	abstract fun exchangeDao(): ExchangeDao
}