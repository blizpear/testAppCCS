package com.blizpear.testAppCCS.features.popularScreen.data.models

data class PopularExchangeModel(
	val base: String,
	val date: String,
	val rates: Map<String, Double>
)