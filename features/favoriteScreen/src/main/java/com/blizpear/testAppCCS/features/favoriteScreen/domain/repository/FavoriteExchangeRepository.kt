package com.blizpear.testAppCCS.features.favoriteScreen.domain.repository

import com.blizpear.testAppCCS.features.favoriteScreen.domain.entity.FavoriteExchange
import kotlinx.coroutines.flow.Flow

interface FavoriteExchangeRepository {

	fun getFavoriteExchange(): Flow<List<FavoriteExchange>>

	suspend fun saveFavorites(data: List<FavoriteExchange>)
}