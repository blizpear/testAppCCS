package com.blizpear.testAppCCS.features.popularScreen.domain.usecase

import com.blizpear.testAppCCS.features.popularScreen.domain.repository.PopularExchangeRepository

class GetPopularExchangeUseCase(
	private val repository: PopularExchangeRepository
) {

	operator fun invoke() = repository.getPopularExchange()
}