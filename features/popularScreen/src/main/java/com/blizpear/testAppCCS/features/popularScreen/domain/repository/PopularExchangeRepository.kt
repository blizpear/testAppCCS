package com.blizpear.testAppCCS.features.popularScreen.domain.repository

import com.blizpear.testAppCCS.features.popularScreen.domain.entity.PopularExchange
import kotlinx.coroutines.flow.Flow

interface PopularExchangeRepository {

	suspend fun updateLocalStorageWithRemoteData(baseCurrency: String)

	fun getPopularExchange(): Flow<List<PopularExchange>>

	suspend fun saveFavorites(data: List<PopularExchange>)
}