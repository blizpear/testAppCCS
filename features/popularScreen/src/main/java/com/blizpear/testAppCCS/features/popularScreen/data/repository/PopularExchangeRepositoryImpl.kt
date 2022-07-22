package com.blizpear.testAppCCS.features.popularScreen.data.repository

import com.blizpear.testAppCCS.features.popularScreen.data.datasource.PopularExchangeDatasource
import com.blizpear.testAppCCS.features.popularScreen.data.mappers.toDatabaseEntityList
import com.blizpear.testAppCCS.features.popularScreen.data.mappers.toEntityList
import com.blizpear.testAppCCS.features.popularScreen.data.mappers.toListDatabaseEntities
import com.blizpear.testAppCCS.features.popularScreen.data.mappers.toStringList
import com.blizpear.testAppCCS.features.popularScreen.domain.entity.PopularExchange
import com.blizpear.testAppCCS.features.popularScreen.domain.repository.PopularExchangeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class PopularExchangeRepositoryImpl(
	private val datasource: PopularExchangeDatasource
) : PopularExchangeRepository {

	override suspend fun updateLocalStorageWithRemoteData(baseCurrency: String) {
		withContext(Dispatchers.IO) {
			val remoteData = datasource.getLatestExchangesFromNetwork(baseCurrency).toDatabaseEntityList()
			val favoritesList = datasource.getFavorites().toStringList()

			remoteData.map {
				it.isFavorite = favoritesList.contains(it.currencyName)
			}
			datasource.replaceOldDataWithNewData(remoteData)
		}
	}

	override fun getPopularExchange(): Flow<List<PopularExchange>> =
		datasource.getLatestExchanges().toEntityList()

	override suspend fun saveFavorites(data: List<PopularExchange>) {
		withContext(Dispatchers.IO) {
			val favoritesList: MutableList<String> = mutableListOf()

			data.forEach {
				if (it.isFavorite) favoritesList.add(it.currencyName)
			}

			datasource.saveFavoritesList(favoritesList.toListDatabaseEntities())
			datasource.replaceOldDataWithNewData(data.toDatabaseEntityList())
		}
	}
}