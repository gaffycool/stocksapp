package com.app.commondomain.interactors

import com.app.commondomain.repository.StocksRepository
import com.app.commondomain.model.StockModel
import javax.inject.Inject

/**
 * Interactor to save muscle into favorite list
 *
 */
class SaveToFavoriteInteractor @Inject constructor(
    private val muscleRepository: StocksRepository
) {
    suspend fun save(watchlistModel: StockModel) = muscleRepository.saveToWatchlist(watchlistModel)
}
