package com.blizpear.testAppCCS.feature.changecurrencyscreen.domain.usecase

import com.blizpear.testAppCCS.feature.changecurrencyscreen.domain.repository.ChangeCurrencyRepository

class GetCurrenciesUseCase(
	private val repository: ChangeCurrencyRepository
) {

	suspend operator fun invoke(currentCurrency: String) = repository.getCurrencies(currentCurrency)
}