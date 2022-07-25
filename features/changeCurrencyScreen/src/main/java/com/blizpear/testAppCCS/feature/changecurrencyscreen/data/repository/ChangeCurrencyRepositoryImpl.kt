package com.blizpear.testAppCCS.feature.changecurrencyscreen.data.repository

import com.blizpear.testAppCCS.feature.changecurrencyscreen.data.datasource.ChangeCurrencyDatasource
import com.blizpear.testAppCCS.feature.changecurrencyscreen.data.mappers.toEntity
import com.blizpear.testAppCCS.feature.changecurrencyscreen.domain.entity.Currencies
import com.blizpear.testAppCCS.feature.changecurrencyscreen.domain.repository.ChangeCurrencyRepository

class ChangeCurrencyRepositoryImpl(
	private val datasource: ChangeCurrencyDatasource
) : ChangeCurrencyRepository {

	override suspend fun getCurrencies(current: String): List<Currencies> =
		datasource.getCurrencies().toEntity(current)
}