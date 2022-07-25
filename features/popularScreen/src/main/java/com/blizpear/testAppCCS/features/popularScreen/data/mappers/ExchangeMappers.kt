package com.blizpear.testAppCCS.features.popularScreen.data.mappers

import com.blizpear.testAppCCS.feature.database.entities.FavoritesEntity
import com.blizpear.testAppCCS.feature.database.entities.PopularEntity
import com.blizpear.testAppCCS.features.popularScreen.data.models.PopularExchangeModel
import com.blizpear.testAppCCS.features.popularScreen.domain.entity.PopularExchange
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun PopularExchangeModel.toDatabaseEntityList(): List<PopularEntity> {
	val exchangesList = mutableListOf<PopularEntity>()

	rates.forEach { (currencyName, currencyValue) ->
		exchangesList.add(
			PopularEntity(
				currencyName = currencyName,
				currencyValue = currencyValue,
				isBase = currencyName == base
			)
		)
	}

	return exchangesList
}

fun PopularEntity.toEntity() = PopularExchange(
	currencyName = currencyName,
	currencyValue = currencyValue,
	isBase = isBase,
	isFavorite = isFavorite
)

fun List<PopularEntity>.toEntityList() = map(PopularEntity::toEntity)

fun Flow<List<PopularEntity>>.toEntityList() = map(List<PopularEntity>::toEntityList)

fun String.toFavoriteDatabaseEntity() = FavoritesEntity(
	currencyName = this
)

fun List<String>.toListDatabaseEntities() = map(String::toFavoriteDatabaseEntity)

fun FavoritesEntity.toSingleString() = currencyName

fun List<FavoritesEntity>.toStringList() = map(FavoritesEntity::toSingleString)

fun PopularExchange.toDatabaseEntity() = PopularEntity(
	currencyName = currencyName,
	currencyValue = currencyValue,
	isBase = isBase,
	isFavorite = isFavorite
)

fun List<PopularExchange>.toDatabaseEntityList() = map(PopularExchange::toDatabaseEntity)