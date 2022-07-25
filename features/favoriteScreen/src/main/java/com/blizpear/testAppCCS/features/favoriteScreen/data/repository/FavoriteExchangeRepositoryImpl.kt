package com.blizpear.testAppCCS.features.favoriteScreen.data.repository

import com.blizpear.testAppCCS.features.favoriteScreen.data.datasource.FavoriteExchangeDatasource
import com.blizpear.testAppCCS.features.favoriteScreen.data.mappers.toDatabaseEntityList
import com.blizpear.testAppCCS.features.favoriteScreen.data.mappers.toEntityList
import com.blizpear.testAppCCS.features.favoriteScreen.data.mappers.toListDatabaseEntities
import com.blizpear.testAppCCS.features.favoriteScreen.domain.entity.FavoriteExchange
import com.blizpear.testAppCCS.features.favoriteScreen.domain.repository.FavoriteExchangeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class FavoriteExchangeRepositoryImpl(
	private val datasource: FavoriteExchangeDatasource
) : FavoriteExchangeRepository {

	override fun getFavoriteExchange(): Flow<List<FavoriteExchange>> =
		datasource.getFavoriteExchanges().toEntityList()

	override suspend fun saveFavorites(data: List<FavoriteExchange>) {
		withContext(Dispatchers.IO) {
			val favoritesList: MutableList<String> = mutableListOf()

			data.forEach {
				if (it.isFavorite) favoritesList.add(it.currencyName)
			}

			datasource.saveFavoritesList(favoritesList.toListDatabaseEntities())
			datasource.updateData(data.toDatabaseEntityList())
		}
	}
}