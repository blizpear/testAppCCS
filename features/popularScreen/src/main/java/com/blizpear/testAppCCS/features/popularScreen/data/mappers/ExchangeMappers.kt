package com.blizpear.testAppCCS.features.popularScreen.data.mappers

import com.blizpear.testAppCCS.feature.database.entities.FavoritesEntity
import com.blizpear.testAppCCS.feature.database.entities.ExchangeEntity
import com.blizpear.testAppCCS.features.popularScreen.data.models.PopularExchangeModel
import com.blizpear.testAppCCS.features.popularScreen.domain.entity.PopularExchange
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun PopularExchangeModel.toDatabaseEntityList(): List<ExchangeEntity> {
	val exchangesList = mutableListOf<ExchangeEntity>()

	rates.forEach { (currencyName, currencyValue) ->
		exchangesList.add(
			ExchangeEntity(
				currencyName = currencyName,
				currencyValue = currencyValue,
				isBase = currencyName == base
			)
		)
	}

	return exchangesList
}

fun ExchangeEntity.toEntity() = PopularExchange(
	currencyName = currencyName,
	currencyValue = currencyValue,
	isBase = isBase,
	isFavorite = isFavorite
)

fun List<ExchangeEntity>.toEntityList() = map(ExchangeEntity::toEntity)

fun Flow<List<ExchangeEntity>>.toEntityList() = map(List<ExchangeEntity>::toEntityList)

fun String.toFavoriteDatabaseEntity() = FavoritesEntity(
	currencyName = this
)

fun List<String>.toListDatabaseEntities() = map(String::toFavoriteDatabaseEntity)

fun FavoritesEntity.toSingleString() = currencyName

fun List<FavoritesEntity>.toStringList() = map(FavoritesEntity::toSingleString)

fun PopularExchange.toDatabaseEntity() = ExchangeEntity(
	currencyName = currencyName,
	currencyValue = currencyValue,
	isBase = isBase,
	isFavorite = isFavorite
)

fun List<PopularExchange>.toDatabaseEntityList() = map(PopularExchange::toDatabaseEntity)