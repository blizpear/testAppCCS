package com.blizpear.testAppCCS.features.favoriteScreen.domain.entity

data class FavoriteExchange(
	val currencyName: String,
	val currencyValue: Double,
	val isBase: Boolean = false,
	var isFavorite: Boolean = false
)