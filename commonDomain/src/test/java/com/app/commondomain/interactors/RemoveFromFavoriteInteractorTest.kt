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
class RemoveFromFavoriteInteractorTest {

    private val muscleRepository: StocksRepository = mockk()
    private lateinit var getFavoriteStocksInteractor: RemoveFromFavoriteInteractor

    @Before
    fun setUp() {
        getFavoriteStocksInteractor = RemoveFromFavoriteInteractor(muscleRepository)
    }

    /***
     * GIVEN muscleRepository.removeFromWatchlist
     * WHEN i invoke interactor
     * THEN i expect it execute successfully
     */
    @Test
    fun testRemoveFromWatchlist() = runTest {
        val model: StockModel = mockk()
        //given
        coJustRun { muscleRepository.removeFromWatchlist(model) }

        //when
        getFavoriteStocksInteractor.remove(model)

        //then
        coVerify { muscleRepository.removeFromWatchlist(model) }
    }
}