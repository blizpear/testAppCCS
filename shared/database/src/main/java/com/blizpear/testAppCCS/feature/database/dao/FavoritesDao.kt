package com.blizpear.testAppCCS.feature.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.blizpear.testAppCCS.feature.database.entities.FavoritesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertListToFavorite(data: List<FavoritesEntity>)

	@Query("DELETE FROM favorites_table")
	suspend fun deleteAll()

	@Transaction
	suspend fun insertListToFavorites(data: List<FavoritesEntity>) {
		deleteAll()
		insertListToFavorite(data)
	}

	@Query("SELECT * FROM favorites_table")
	fun getFavorites(): Flow<List<FavoritesEntity>>
}