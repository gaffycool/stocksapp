package com.app.stockapp.ui.feature.favorite

import androidx.lifecycle.viewModelScope
import com.app.commondomain.interactors.GetFavoriteStocksInteractor
import com.app.commondomain.interactors.RemoveFromFavoriteInteractor
import com.app.commondomain.model.StockModel
import com.app.stockapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject internal constructor(
    getFavoriteStocksInteractor: GetFavoriteStocksInteractor,
    private val removeFromFavoriteInteractor: RemoveFromFavoriteInteractor,
) : BaseViewModel<FavoriteViewState>() {

    override fun defaultState(): FavoriteViewState = FavoriteViewState.Empty

    init {
        viewModelScope.launch {
            val stocks = getFavoriteStocksInteractor.get()
            newState { FavoriteViewState.Content(stocks) }
        }
    }

    fun actionFavorite(stockModel: StockModel) {
        viewModelScope.launch {
            removeFromFavoriteInteractor.remove(stockModel)
        }
    }
}

sealed class FavoriteViewState {
    data object Empty : FavoriteViewState()
    data class Content(val stocks: Flow<List<StockModel>>) : FavoriteViewState()
}
