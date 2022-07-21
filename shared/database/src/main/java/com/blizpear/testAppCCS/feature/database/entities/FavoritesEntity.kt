package com.blizpear.testAppCCS.feature.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
	tableName = "favorites_table"
)
data class FavoritesEntity(
	@PrimaryKey(autoGenerate = false)
	val currencyName: String
)