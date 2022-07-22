package com.blizpear.testAppCCS.features.popularScreen.domain.entity

data class PopularExchange(
	val currencyName: String,
	val currencyValue: Double,
	val isBase: Boolean = false,
	var isFavorite: Boolean = false
)