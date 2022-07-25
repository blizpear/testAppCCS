package com.blizpear.testAppCCS.feature.database.di

import android.content.Context
import androidx.room.Room
import com.blizpear.testAppCCS.feature.database.dao.FavoritesDao
import com.blizpear.testAppCCS.feature.database.dao.ExchangeDao
import com.blizpear.testAppCCS.feature.database.database.ExchangeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

	@Provides
	@Singleton
	fun provideExchangeDatabase(
		@ApplicationContext context: Context
	): ExchangeDatabase =
		Room.databaseBuilder(
			context,
			ExchangeDatabase::class.java,
			ExchangeDatabase.DATABASE_NAME
		)
			.fallbackToDestructiveMigration()
			.build()

	@Provides
	@Singleton
	fun provideFavoritesDao(db: ExchangeDatabase): FavoritesDao =
		db.favoriteDao()

	@Provides
	@Singleton
	fun providePopularDao(db: ExchangeDatabase): ExchangeDao =
		db.exchangeDao()
}