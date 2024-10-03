package com.app.commondomain.interactors

import com.app.commondomain.repository.StocksRepository
import com.app.commondomain.model.StockModel
import javax.inject.Inject

/**
 * Interactor to remove muscle from favorite list
 *
 */
class RemoveFromFavoriteInteractor @Inject constructor(
    private val muscleRepository: StocksRepository
) {
    suspend fun remove(model: StockModel) =
        muscleRepository.removeFromWatchlist(model)
}
