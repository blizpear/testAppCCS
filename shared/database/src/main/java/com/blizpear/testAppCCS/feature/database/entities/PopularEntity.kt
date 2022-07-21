package com.blizpear.testAppCCS.feature.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
	tableName = "popular_table"
)
data class PopularEntity(
	@PrimaryKey(autoGenerate = false)
	val currencyName: String,
	val currencyValue: Double,
	val isBase: Boolean = false
)