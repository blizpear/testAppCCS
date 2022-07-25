package com.blizpear.testAppCCS.features.popularScreen.data.datasource

import com.blizpear.testAppCCS.feature.database.entities.FavoritesEntity
import com.blizpear.testAppCCS.feature.database.entities.PopularEntity
import com.blizpear.testAppCCS.features.popularScreen.data.models.PopularExchangeModel
import kotlinx.coroutines.flow.Flow

interface PopularExchangeDatasource {

	suspend fun getLatestExchangesFromNetwork(baseCurrency: String): PopularExchangeModel

	suspend fun replaceOldDataWithNewData(remoteData: List<PopularEntity>)

	fun getLatestExchanges(): Flow<List<PopularEntity>>

	suspend fun getFavorites(): List<FavoritesEntity>

	suspend fun saveFavoritesList(favoritesList: List<FavoritesEntity>)
}