package com.blizpear.testAppCCS.network.di

import com.blizpear.testAppCCS.network.RetrofitProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

	@Provides
	@Singleton
	fun provideRetrofit(): Retrofit = RetrofitProvider.retrofit
}