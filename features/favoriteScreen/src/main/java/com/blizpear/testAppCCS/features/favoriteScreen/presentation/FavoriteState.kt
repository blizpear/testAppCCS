package com.blizpear.testAppCCS.features.favoriteScreen.presentation

import com.blizpear.testAppCCS.features.favoriteScreen.domain.entity.FavoriteExchange

sealed class FavoriteState {

	object Initialize : FavoriteState()

	object Loading : FavoriteState()

	object Error : FavoriteState()

	class Content(
		val data: List<FavoriteExchange>,
		val baseCurrency: String
	) : FavoriteState()
}