package com.blizpear.testAppCCS.feature.changecurrencyscreen.data.model

import com.squareup.moshi.Json

data class CurrenciesModel(
	@Json(name = "symbols")
	val currencies: Map<String, String>
)