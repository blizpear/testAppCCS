package com.blizpear.testAppCCS.features.popularScreen.data.datasource

import com.blizpear.testAppCCS.feature.database.dao.FavoritesDao
import com.blizpear.testAppCCS.feature.database.dao.PopularDao
import com.blizpear.testAppCCS.feature.database.entities.FavoritesEntity
import com.blizpear.testAppCCS.feature.database.entities.PopularEntity
import com.blizpear.testAppCCS.features.popularScreen.data.api.ExchangeApi
import com.blizpear.testAppCCS.features.popularScreen.data.models.PopularExchangeModel
import kotlinx.coroutines.flow.Flow

class PopularExchangeDatasourceImpl(
	private val api: ExchangeApi,
	private val popularDao: PopularDao,
	private val favoritesDao: FavoritesDao
) : PopularExchangeDatasource {

	override suspend fun getLatestExchangesFromNetwork(baseCurrency: String): PopularExchangeModel =
		api.getLatest(baseCurrency)

	override suspend fun replaceOldDataWithNewData(remoteData: List<PopularEntity>) =
		popularDao.replaceOldDataWithNewData(remoteData)

	override fun getLatestExchanges(): Flow<List<PopularEntity>> =
		popularDao.getLatestExchanges()

	override suspend fun getFavorites(): List<FavoritesEntity> =
		favoritesDao.getFavorites()

	override suspend fun saveFavoritesList(favoritesList: List<FavoritesEntity>) =
		favoritesDao.insertListToFavorites(data = favoritesList)
}