package com.blizpear.testAppCCS.features.popularScreen.domain.usecase

import com.blizpear.testAppCCS.features.popularScreen.domain.repository.PopularExchangeRepository

class UpdateLocalStorageUseCase(
	private val repository: PopularExchangeRepository
) {

	suspend operator fun invoke(baseCurrency: String) =
		repository.updateLocalStorageWithRemoteData(baseCurrency)
}