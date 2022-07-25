package com.blizpear.testAppCCS.feature.changecurrencyscreen.data.api

import com.blizpear.testAppCCS.feature.changecurrencyscreen.data.model.CurrenciesModel
import retrofit2.http.GET

interface CurrencyApi {

	@GET("symbols")
	suspend fun getCurrenciesList(): CurrenciesModel
}