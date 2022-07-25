package com.blizpear.testAppCCS.feature.changecurrencyscreen.domain.repository

import com.blizpear.testAppCCS.feature.changecurrencyscreen.domain.entity.Currencies

interface ChangeCurrencyRepository {

	suspend fun getCurrencies(current: String): List<Currencies>
}