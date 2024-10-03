package com.app.stockapp.ui.feature.favorite

import com.app.commondomain.interactors.GetFavoriteStocksInteractor
import com.app.commondomain.interactors.RemoveFromFavoriteInteractor
import com.app.commondomain.model.StockModel
import com.app.stockapp.MainCoroutineRule
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals

@ExperimentalCoroutinesApi
class FavoriteViewModelTest {

    private val getFavoriteStocksInteractor: GetFavoriteStocksInteractor = mockk()
    private val removeFromFavoriteInteractor: RemoveFromFavoriteInteractor = mockk()
    private lateinit var viewModel: FavoriteViewModel

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val stockModel = StockModel(
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
    private val listLiveData: Flow<List<StockModel>> =
        flow { listOf(stockModel) }

    @Before
    fun setUp() {
        coEvery { getFavoriteStocksInteractor.get() } returns listLiveData
        viewModel = FavoriteViewModel(
            getFavoriteStocksInteractor,
            removeFromFavoriteInteractor
        )
    }

    @Test
    fun testInit() = runTest {
        //then
        val expectedState = viewModel.uiState.value
        assertEquals(FavoriteViewState.Content(listLiveData), expectedState)
    }

    @Test
    fun testActionFavorite() = runTest {
        //when
        val stockModel: StockModel = mockk()
        coJustRun { removeFromFavoriteInteractor.remove(stockModel) }

        viewModel.actionFavorite(stockModel)
        advanceUntilIdle()

        //then
        coVerify { removeFromFavoriteInteractor.remove(stockModel) }
    }
}
