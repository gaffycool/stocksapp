package com.app.commondomain.repository

import com.app.commondomain.model.StockModel
import kotlinx.coroutines.flow.Flow

interface StocksRepository {
    suspend fun fetchStocks(): List<StockModel>
    fun getFavoriteStocks(): Flow<List<StockModel>>
    suspend fun saveToWatchlist(stockModel: StockModel)
    suspend fun removeFromWatchlist(stockModel: StockModel)
    suspend fun findWatchlist(symbol: String): Boolean
}
