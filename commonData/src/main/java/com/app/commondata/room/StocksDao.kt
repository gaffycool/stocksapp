package com.app.commondata.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * The Data Access Object for the StockModel class.
 */
@Dao
interface StocksDao {
    @Query("SELECT * FROM stocks ORDER BY name")
    fun getFavoriteStocks(): Flow<List<StockEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(stockEntity: StockEntity)

    @Delete
    suspend fun delete(vararg stockEntity: StockEntity)

    @Query("SELECT * FROM stocks WHERE symbol = :symbol LIMIT 1")
    suspend fun findWatchlist(symbol: String): StockEntity?
}
