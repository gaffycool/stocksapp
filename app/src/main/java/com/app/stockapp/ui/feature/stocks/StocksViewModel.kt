package com.app.stockapp.ui.feature.stocks

import androidx.lifecycle.viewModelScope
import com.app.commondomain.model.StockModel
import com.app.commondomain.interactors.GetStocksInteractor
import com.app.commondomain.interactors.RemoveFromFavoriteInteractor
import com.app.commondomain.interactors.SaveToFavoriteInteractor
import com.app.stockapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StocksViewModel @Inject internal constructor(
    private val getStocksInteractor: GetStocksInteractor,
    private val saveToFavoriteInteractor: SaveToFavoriteInteractor,
    private val removeFromFavoriteInteractor: RemoveFromFavoriteInteractor,
) : BaseViewModel<StocksViewState>() {

    override fun defaultState(): StocksViewState = StocksViewState()

    init {
        fetchStocks()
    }

    fun fetchStocks() {
        viewModelScope.launch {
            val stocks = getStocksInteractor.get()
            newState { it.copy(stocks = stocks) }
        }
    }

    fun actionFavorite(index: Int) = requireState { vmState ->
        val model = vmState.stocks[index]
        viewModelScope.launch {
            if (model.isFavorite) {
                removeFromFavoriteInteractor.remove(model)
                newState {
                    vmState.copy(
                        stocks = vmState.stocks.toMutableList().apply {
                            set(index, model.copy(isFavorite = false))
                        }.toList()
                    )
                }
            } else {
                val favoriteModel = model.copy(isFavorite = true)
                saveToFavoriteInteractor.save(favoriteModel)
                newState {
                    vmState.copy(
                        stocks = vmState.stocks.toMutableList().apply {
                            set(index, favoriteModel)
                        }.toList()
                    )
                }
            }
        }
    }
}

data class StocksViewState(
    val stocks: List<StockModel> = emptyList(),
)
