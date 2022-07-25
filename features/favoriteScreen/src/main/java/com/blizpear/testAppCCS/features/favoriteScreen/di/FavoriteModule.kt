package com.blizpear.testAppCCS.features.favoriteScreen.di

import com.blizpear.testAppCCS.feature.database.dao.ExchangeDao
import com.blizpear.testAppCCS.feature.database.dao.FavoritesDao
import com.blizpear.testAppCCS.features.favoriteScreen.data.datasource.FavoriteExchangeDatasource
import com.blizpear.testAppCCS.features.favoriteScreen.data.datasource.FavoriteExchangeDatasourceImpl
import com.blizpear.testAppCCS.features.favoriteScreen.data.repository.FavoriteExchangeRepositoryImpl
import com.blizpear.testAppCCS.features.favoriteScreen.domain.repository.FavoriteExchangeRepository
import com.blizpear.testAppCCS.features.favoriteScreen.domain.usecase.GetFavoriteExchangeUseCase
import com.blizpear.testAppCCS.features.favoriteScreen.domain.usecase.SaveFavoritesUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [FavoriteModule.FavoriteBinds::class])
@InstallIn(SingletonComponent::class)
object FavoriteModule {

	@Module
	@InstallIn(SingletonComponent::class)
	interface FavoriteBinds {

		@Binds
		fun bindDatasource(impl: FavoriteExchangeDatasourceImpl): FavoriteExchangeDatasource

		@Binds
		fun bindRepository(impl: FavoriteExchangeRepositoryImpl): FavoriteExchangeRepository
	}

	@Provides
	@Singleton
	fun provideDatasource(
		exchangeDao: ExchangeDao,
		favoritesDao: FavoritesDao
	): FavoriteExchangeDatasourceImpl =
		FavoriteExchangeDatasourceImpl(
			exchangeDao = exchangeDao,
			favoritesDao = favoritesDao
		)

	@Provides
	@Singleton
	fun provideRepository(
		datasource: FavoriteExchangeDatasource
	): FavoriteExchangeRepositoryImpl =
		FavoriteExchangeRepositoryImpl(datasource = datasource)

	@Provides
	@Singleton
	fun provideGetFavoriteExchangeUseCase(repository: FavoriteExchangeRepository): GetFavoriteExchangeUseCase =
		GetFavoriteExchangeUseCase(repository = repository)

	@Provides
	@Singleton
	fun provideSaveFavoritesUseCase(repository: FavoriteExchangeRepository): SaveFavoritesUseCase =
		SaveFavoritesUseCase(repository = repository)
}