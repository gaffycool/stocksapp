package com.app.commondomain.interactors

import com.app.commondomain.model.StockModel
import com.app.commondomain.repository.StocksRepository
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class SaveToFavoriteInteractorTest {

    private val muscleRepository: StocksRepository = mockk()
    private lateinit var getFavoriteStocksInteractor: SaveToFavoriteInteractor

    @Before
    fun setUp() {
        getFavoriteStocksInteractor = SaveToFavoriteInteractor(muscleRepository)
    }

    /***
     * GIVEN muscleRepository.saveToWatchlist
     * WHEN i invoke interactor
     * THEN i expect it execute successfully
     */
    @Test
    fun testSaveToWatchlist() = runTest {
        val model: StockModel = mockk()
        //given
        coJustRun { muscleRepository.saveToWatchlist(model) }

        //when
        getFavoriteStocksInteractor.save(model)

        //then
        coVerify { muscleRepository.saveToWatchlist(model) }
    }
}