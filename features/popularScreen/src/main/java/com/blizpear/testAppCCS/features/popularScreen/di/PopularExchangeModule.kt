package com.blizpear.testAppCCS.features.popularScreen.di

import com.blizpear.testAppCCS.feature.database.dao.FavoritesDao
import com.blizpear.testAppCCS.feature.database.dao.ExchangeDao
import com.blizpear.testAppCCS.features.popularScreen.data.api.ExchangeApi
import com.blizpear.testAppCCS.features.popularScreen.data.datasource.PopularExchangeDatasource
import com.blizpear.testAppCCS.features.popularScreen.data.datasource.PopularExchangeDatasourceImpl
import com.blizpear.testAppCCS.features.popularScreen.data.repository.PopularExchangeRepositoryImpl
import com.blizpear.testAppCCS.features.popularScreen.domain.repository.PopularExchangeRepository
import com.blizpear.testAppCCS.features.popularScreen.domain.usecase.GetPopularExchangeUseCase
import com.blizpear.testAppCCS.features.popularScreen.domain.usecase.SaveFavoritesUseCase
import com.blizpear.testAppCCS.features.popularScreen.domain.usecase.UpdateLocalStorageUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [PopularExchangeModule.PopularExchangeBinds::class])
@InstallIn(SingletonComponent::class)
object PopularExchangeModule {

	@Provides
	@Singleton
	fun provideExchangeApi(retrofit: Retrofit): ExchangeApi =
		retrofit.create(ExchangeApi::class.java)

	@Module
	@InstallIn(SingletonComponent::class)
	interface PopularExchangeBinds {

		@Binds
		fun bindDatasource(impl: PopularExchangeDatasourceImpl): PopularExchangeDatasource

		@Binds
		fun bindRepository(impl: PopularExchangeRepositoryImpl): PopularExchangeRepository
	}

	@Provides
	@Singleton
	fun provideDatasource(
		api: ExchangeApi,
		exchangeDao: ExchangeDao,
		favoritesDao: FavoritesDao
	): PopularExchangeDatasourceImpl =
		PopularExchangeDatasourceImpl(
			api = api,
			exchangeDao = exchangeDao,
			favoritesDao = favoritesDao
		)

	@Provides
	@Singleton
	fun provideRepository(
		datasource: PopularExchangeDatasource
	): PopularExchangeRepositoryImpl =
		PopularExchangeRepositoryImpl(datasource = datasource)

	@Provides
	@Singleton
	fun provideGetPopularExchangeUseCase(repository: PopularExchangeRepository): GetPopularExchangeUseCase =
		GetPopularExchangeUseCase(repository = repository)

	@Provides
	@Singleton
	fun provideUpdateLocalStorageUseCase(repository: PopularExchangeRepository): UpdateLocalStorageUseCase =
		UpdateLocalStorageUseCase(repository = repository)

	@Provides
	@Singleton
	fun provideSaveFavoritesUseCase(repository: PopularExchangeRepository): SaveFavoritesUseCase =
		SaveFavoritesUseCase(repository = repository)
}