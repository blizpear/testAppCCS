package com.blizpear.testAppCCS.features.popularScreen.presentation

import com.blizpear.testAppCCS.features.popularScreen.domain.entity.PopularExchange

sealed class PopularExchangeState {
	object Initialize : PopularExchangeState()

	object Loading : PopularExchangeState()

	object Error : PopularExchangeState()

	class Content(
		val data: List<PopularExchange>,
		val baseCurrency: String
	) : PopularExchangeState()
}