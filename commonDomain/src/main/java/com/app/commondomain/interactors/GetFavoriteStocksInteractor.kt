package com.app.commondomain.interactors

import com.app.commondomain.model.StockModel
import com.app.commondomain.repository.StocksRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Interactor to fetch favorite stocks
 *
 */
class GetFavoriteStocksInteractor @Inject constructor(
    private val muscleRepository: StocksRepository
) {
    fun get(): Flow<List<StockModel>> = muscleRepository.getFavoriteStocks()
}
