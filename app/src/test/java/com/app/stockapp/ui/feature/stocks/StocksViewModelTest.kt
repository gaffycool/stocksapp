package com.app.stockapp.ui.feature.stocks

import com.app.stockapp.MainCoroutineRule
import com.app.commondomain.model.StockModel
import com.app.commondomain.interactors.GetStocksInteractor
import com.app.commondomain.interactors.RemoveFromFavoriteInteractor
import com.app.commondomain.interactors.SaveToFavoriteInteractor
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals

@ExperimentalCoroutinesApi
class StocksViewModelTest {

    private val getStocksInteractor: GetStocksInteractor = mockk()
    private val saveToFavoriteInteractor: SaveToFavoriteInteractor = mockk()
    private val removeFromFavoriteInteractor: RemoveFromFavoriteInteractor = mockk()

    private lateinit var viewModel: StocksViewModel

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private var stockModel = StockModel(
        country = "country",
        currency = "currency",
        exchange = "exchange",
        figiCode = "figi_code",
        micCode = "mic_code",
        name = "name",
        symbol = "symbol",
        type = "type",
        isFavorite = true
    )

    @Before
    fun setUp() {
        coEvery { getStocksInteractor.get() } returns emptyList()
        viewModel = StocksViewModel(
            getStocksInteractor,
            saveToFavoriteInteractor,
            removeFromFavoriteInteractor
        )
    }

    /***
     * GIVEN getStocksInteractor return list of StockModel
     * WHEN i invoke fetchStocks
     * THEN i expect viewstate should updated with same list of StockModel
     */
    @Test
    fun testFetchMuscles() = runTest {
        //given
        val stockModels: List<StockModel> = listOf(stockModel)
        coEvery { getStocksInteractor.get() } returns stockModels

        viewModel.fetchStocks()
        advanceUntilIdle()

        //then
        assertEquals(stockModels, viewModel.uiState.value.stocks)
    }

    /***
     * GIVEN testFetchMuscles() and testMuscleSelected()
     * AND removeFromFavoriteInteractor with favorite true
     * WHEN i invoke actionFavorite
     * THEN i expect viewstate should updated with favorite value
     */
    @Test
    fun testActionRemoveFromFavorite() = runTest {
        //given
        testFetchMuscles()

        coJustRun { removeFromFavoriteInteractor.remove(stockModel) }

        //when
        viewModel.actionFavorite(0)
        advanceUntilIdle()

        //then
        assertEquals(
            stockModel.copy(isFavorite = false),
            viewModel.uiState.value.stocks.first()
        )
    }

    /***
     * GIVEN testFetchMuscles() and testMuscleSelected()
     * AND saveToFavoriteInteractor with favorite false
     * WHEN i invoke actionFavorite
     * THEN i expect viewstate should updated with favorite value
     */
    @Test
    fun testActionSaveToFavorite() = runTest {
        //given
        stockModel = stockModel.copy(isFavorite = false)
        testFetchMuscles()
        val expected = stockModel.copy(isFavorite = true)
        coJustRun { saveToFavoriteInteractor.save(expected) }

        //when
        viewModel.actionFavorite(0)
        advanceUntilIdle()

        //then
        assertEquals(
            expected,
            viewModel.uiState.value.stocks.first()
        )
    }
}
