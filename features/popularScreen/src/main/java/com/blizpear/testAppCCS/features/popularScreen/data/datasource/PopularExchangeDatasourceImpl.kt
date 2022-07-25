package com.blizpear.testAppCCS.features.popularScreen.data.datasource

import com.blizpear.testAppCCS.feature.database.dao.FavoritesDao
import com.blizpear.testAppCCS.feature.database.dao.ExchangeDao
import com.blizpear.testAppCCS.feature.database.entities.FavoritesEntity
import com.blizpear.testAppCCS.feature.database.entities.ExchangeEntity
import com.blizpear.testAppCCS.features.popularScreen.data.api.ExchangeApi
import com.blizpear.testAppCCS.features.popularScreen.data.models.PopularExchangeModel
import kotlinx.coroutines.flow.Flow

class PopularExchangeDatasourceImpl(
	private val api: ExchangeApi,
	private val exchangeDao: ExchangeDao,
	private val favoritesDao: FavoritesDao
) : PopularExchangeDatasource {

	override suspend fun getLatestExchangesFromNetwork(baseCurrency: String): PopularExchangeModel =
		api.getLatest(baseCurrency)

	override suspend fun replaceOldDataWithNewData(remoteData: List<ExchangeEntity>) =
		exchangeDao.replaceOldDataWithNewData(remoteData)

	override fun getLatestExchanges(): Flow<List<ExchangeEntity>> =
		exchangeDao.getLatestExchanges()

	override suspend fun getFavorites(): List<FavoritesEntity> =
		favoritesDao.getFavorites()

	override suspend fun saveFavoritesList(favoritesList: List<FavoritesEntity>) =
		favoritesDao.insertListToFavorites(data = favoritesList)
}