package com.blizpear.testAppCCS.features.favoriteScreen.domain.usecase

import com.blizpear.testAppCCS.features.favoriteScreen.domain.repository.FavoriteExchangeRepository

class GetFavoriteExchangeUseCase(
	private val repository: FavoriteExchangeRepository
) {

	operator fun invoke() = repository.getFavoriteExchange()
}