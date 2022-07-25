package com.blizpear.testAppCCS.features.favoriteScreen.data.datasource

import com.blizpear.testAppCCS.feature.database.dao.ExchangeDao
import com.blizpear.testAppCCS.feature.database.dao.FavoritesDao
import com.blizpear.testAppCCS.feature.database.entities.ExchangeEntity
import com.blizpear.testAppCCS.feature.database.entities.FavoritesEntity
import kotlinx.coroutines.flow.Flow

class FavoriteExchangeDatasourceImpl(
	private val exchangeDao: ExchangeDao,
	private val favoritesDao: FavoritesDao
) : FavoriteExchangeDatasource {

	override fun getFavoriteExchanges(): Flow<List<ExchangeEntity>> =
		exchangeDao.getFavoritesExchanges()

	override suspend fun getFavorites(): List<FavoritesEntity> =
		favoritesDao.getFavorites()

	override suspend fun saveFavoritesList(favoritesList: List<FavoritesEntity>) =
		favoritesDao.insertListToFavorites(data = favoritesList)

	override suspend fun updateData(remoteData: List<ExchangeEntity>) =
		exchangeDao.insertExchanges(remoteData)
}