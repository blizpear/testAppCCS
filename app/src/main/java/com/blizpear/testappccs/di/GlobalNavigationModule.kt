package com.blizpear.testappccs.di

import com.blizpear.testappccs.navigation.provideRootScreen
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GlobalNavigationModule {

	@Provides
	@Singleton
	fun provideCicerone(): Cicerone<Router> = Cicerone.create().apply {
		router.newRootScreen(provideRootScreen())
	}

	@Provides
	@Singleton
	fun provideRouter(cicerone: Cicerone<Router>) = cicerone.router

	@Provides
	@Singleton
	fun provideNavigationHolder(cicerone: Cicerone<Router>) = cicerone.getNavigatorHolder()
}