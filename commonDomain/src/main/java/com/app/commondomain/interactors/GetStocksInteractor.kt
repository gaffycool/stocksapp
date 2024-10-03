package com.app.commondomain.interactors

import com.app.commondomain.repository.StocksRepository
import com.app.commondomain.model.StockModel
import javax.inject.Inject

class GetStocksInteractor @Inject constructor(
    private val muscleRepository: StocksRepository
) {
    suspend fun get(): List<StockModel> = muscleRepository.fetchStocks()
}
