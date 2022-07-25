package com.blizpear.testAppCCS.features.favoriteScreen.data.datasource

import com.blizpear.testAppCCS.feature.database.entities.ExchangeEntity
import com.blizpear.testAppCCS.feature.database.entities.FavoritesEntity
import com.blizpear.testAppCCS.features.favoriteScreen.domain.entity.FavoriteExchange
import kotlinx.coroutines.flow.Flow

interface FavoriteExchangeDatasource {

	fun getFavoriteExchanges(): Flow<List<ExchangeEntity>>

	suspend fun getFavorites(): List<FavoritesEntity>

	suspend fun saveFavoritesList(favoritesList: List<FavoritesEntity>)

	suspend fun updateData(remoteData: List<ExchangeEntity>)
}