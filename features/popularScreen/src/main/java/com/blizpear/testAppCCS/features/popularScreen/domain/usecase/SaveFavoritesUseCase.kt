package com.blizpear.testAppCCS.features.popularScreen.domain.usecase

import com.blizpear.testAppCCS.features.popularScreen.domain.entity.PopularExchange
import com.blizpear.testAppCCS.features.popularScreen.domain.repository.PopularExchangeRepository

class SaveFavoritesUseCase(
	private val repository: PopularExchangeRepository
) {

	suspend operator fun invoke(favoritesList: List<PopularExchange>) =
		repository.saveFavorites(favoritesList)
}