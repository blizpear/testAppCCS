package com.blizpear.testAppCCS.feature.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.blizpear.testAppCCS.feature.database.entities.ExchangeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ExchangeDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertExchanges(exchanges: List<ExchangeEntity>)

	@Query("DELETE FROM popular_table")
	suspend fun deleteAll()

	@Query("SELECT * FROM popular_table")
	fun getLatestExchanges(): Flow<List<ExchangeEntity>>

	@Query("SELECT * FROM popular_table WHERE isFavorite == 1")
	fun getFavoritesExchanges(): Flow<List<ExchangeEntity>>

	@Transaction
	suspend fun replaceOldDataWithNewData(data: List<ExchangeEntity>) {
		deleteAll()
		insertExchanges(data)
	}
}