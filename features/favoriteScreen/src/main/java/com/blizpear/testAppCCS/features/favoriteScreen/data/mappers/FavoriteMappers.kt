package com.blizpear.testAppCCS.features.favoriteScreen.data.mappers

import com.blizpear.testAppCCS.feature.database.entities.ExchangeEntity
import com.blizpear.testAppCCS.feature.database.entities.FavoritesEntity
import com.blizpear.testAppCCS.features.favoriteScreen.domain.entity.FavoriteExchange
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun ExchangeEntity.toEntity() = FavoriteExchange(
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

fun FavoriteExchange.toDatabaseEntity() = ExchangeEntity(
	currencyName = currencyName,
	currencyValue = currencyValue,
	isBase = isBase,
	isFavorite = isFavorite
)

fun List<FavoriteExchange>.toDatabaseEntityList() = map(FavoriteExchange::toDatabaseEntity)