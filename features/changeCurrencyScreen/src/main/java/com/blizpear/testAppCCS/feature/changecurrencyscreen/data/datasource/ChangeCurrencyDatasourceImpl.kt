package com.blizpear.testAppCCS.feature.changecurrencyscreen.data.datasource

import com.blizpear.testAppCCS.feature.changecurrencyscreen.data.api.CurrencyApi
import com.blizpear.testAppCCS.feature.changecurrencyscreen.data.model.CurrenciesModel

class ChangeCurrencyDatasourceImpl(
	private val api: CurrencyApi
) : ChangeCurrencyDatasource {

	override suspend fun getCurrencies(): CurrenciesModel =
		api.getCurrenciesList()
}