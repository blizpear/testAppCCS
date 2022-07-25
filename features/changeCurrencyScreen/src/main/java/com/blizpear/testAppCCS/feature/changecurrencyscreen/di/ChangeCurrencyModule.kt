package com.blizpear.testAppCCS.feature.changecurrencyscreen.di

import com.blizpear.testAppCCS.feature.changecurrencyscreen.data.api.CurrencyApi
import com.blizpear.testAppCCS.feature.changecurrencyscreen.data.datasource.ChangeCurrencyDatasource
import com.blizpear.testAppCCS.feature.changecurrencyscreen.data.datasource.ChangeCurrencyDatasourceImpl
import com.blizpear.testAppCCS.feature.changecurrencyscreen.data.repository.ChangeCurrencyRepositoryImpl
import com.blizpear.testAppCCS.feature.changecurrencyscreen.domain.repository.ChangeCurrencyRepository
import com.blizpear.testAppCCS.feature.changecurrencyscreen.domain.usecase.GetCurrenciesUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [ChangeCurrencyModule.ChangeCurrencyBinds::class])
@InstallIn(SingletonComponent::class)
object ChangeCurrencyModule {

	@Provides
	@Suppress
	fun provideCurrencyApi(retrofit: Retrofit): CurrencyApi =
		retrofit.create(CurrencyApi::class.java)

	@Module
	@InstallIn(SingletonComponent::class)
	interface ChangeCurrencyBinds {

		@Binds
		fun bindDatasource(impl: ChangeCurrencyDatasourceImpl): ChangeCurrencyDatasource

		@Binds
		fun bindRepository(impl: ChangeCurrencyRepositoryImpl): ChangeCurrencyRepository
	}

	@Provides
	@Singleton
	fun provideDatasource(api: CurrencyApi): ChangeCurrencyDatasourceImpl =
		ChangeCurrencyDatasourceImpl(api = api)

	@Provides
	@Singleton
	fun provideRepository(datasource: ChangeCurrencyDatasource): ChangeCurrencyRepositoryImpl =
		ChangeCurrencyRepositoryImpl(datasource = datasource)

	@Provides
	@Singleton
	fun provideGetCurrenciesUseCase(repo: ChangeCurrencyRepository): GetCurrenciesUseCase =
		GetCurrenciesUseCase(repository = repo)
}