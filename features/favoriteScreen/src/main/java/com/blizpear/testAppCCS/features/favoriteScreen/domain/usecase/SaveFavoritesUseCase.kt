package com.blizpear.testAppCCS.features.favoriteScreen.domain.usecase

import com.blizpear.testAppCCS.features.favoriteScreen.domain.entity.FavoriteExchange
import com.blizpear.testAppCCS.features.favoriteScreen.domain.repository.FavoriteExchangeRepository

class SaveFavoritesUseCase(
	private val repository: FavoriteExchangeRepository
) {

	suspend operator fun invoke(favoritesList: List<FavoriteExchange>) =
		repository.saveFavorites(favoritesList)
}