package com.blizpear.testAppCCS.feature.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.blizpear.testAppCCS.feature.database.entities.PopularEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PopularDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertExchanges(exchanges: List<PopularEntity>)

	@Query("DELETE FROM popular_table")
	suspend fun deleteAll()

	@Query("SELECT * FROM popular_table")
	fun getLatestExchanges(): Flow<List<PopularEntity>>

	@Transaction
	suspend fun replaceOldDataWithNewData(data: List<PopularEntity>) {
		deleteAll()
		insertExchanges(data)
	}
}