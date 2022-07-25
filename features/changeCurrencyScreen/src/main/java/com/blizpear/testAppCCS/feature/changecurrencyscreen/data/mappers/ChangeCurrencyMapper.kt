package com.blizpear.testAppCCS.feature.changecurrencyscreen.data.mappers

import com.blizpear.testAppCCS.feature.changecurrencyscreen.data.model.CurrenciesModel
import com.blizpear.testAppCCS.feature.changecurrencyscreen.domain.entity.Currencies

fun CurrenciesModel.toEntity(current: String): List<Currencies> {
	val newCurrencies = mutableListOf<Currencies>()

	currencies.forEach { (name, _) ->
		newCurrencies.add(
			Currencies(
				currencies = name,
				isSelected = name == current
			)
		)
	}

	return newCurrencies
}