package com.blizpear.testAppCCS.features.popularScreen.data.api

import com.blizpear.testAppCCS.features.popularScreen.data.models.PopularExchangeModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ExchangeApi {

	@GET("latest")
	suspend fun getLatest(@Query("base") baseCurrency: String): PopularExchangeModel
}