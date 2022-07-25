package com.blizpear.testAppCCS.feature.changecurrencyscreen.data.datasource

import com.blizpear.testAppCCS.feature.changecurrencyscreen.data.model.CurrenciesModel

interface ChangeCurrencyDatasource {

	suspend fun getCurrencies(): CurrenciesModel
}