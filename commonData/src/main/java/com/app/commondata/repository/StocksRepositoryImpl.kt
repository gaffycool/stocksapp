package com.app.commondata.repository

import com.app.commondata.api.ApiService
import com.app.commondata.room.StockEntity
import com.app.commondata.room.StocksDao
import com.app.commondomain.model.StockModel
import com.app.commondomain.repository.StocksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.net.HttpURLConnection
import javax.inject.Inject

/**
 * Repository module for handling data operations.
 *
 */
class StocksRepositoryImpl @Inject constructor(
    private val stocksDao: StocksDao,
    private val apiService: ApiService,
) : StocksRepository {

    override suspend fun fetchStocks(): List<StockModel> {
        val response = apiService.fetchStocks(ApiService.API_KEY)
        return when (response.code()) {
            HttpURLConnection.HTTP_OK -> response.body()?.data?.map {
                it.toDomainModel(isFavorite = findWatchlist(it.symbol))
            } ?: emptyList()

            else -> throw IllegalStateException("Failed to load stocks, Please try again")
        }
    }

    override fun getFavoriteStocks(): Flow<List<StockModel>> =
        stocksDao.getFavoriteStocks().map { it.map { it.transform() } }

    override suspend fun saveToWatchlist(stockModel: StockModel) =
        stocksDao.insert(StockEntity(stockModel))

    override suspend fun removeFromWatchlist(stockModel: StockModel) =
        stocksDao.delete(StockEntity(stockModel))

    override suspend fun findWatchlist(symbol: String): Boolean =
        stocksDao.findWatchlist(symbol) != null
}
