package com.app.commondomain.interactors

import com.app.commondomain.model.StockModel
import com.app.commondomain.repository.StocksRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetStocksInteractorTest {

    private val muscleRepository: StocksRepository = mockk()
    private lateinit var getFavoriteStocksInteractor: GetStocksInteractor

    @Before
    fun setUp() {
        getFavoriteStocksInteractor = GetStocksInteractor(muscleRepository)
    }

    /***
     * GIVEN muscleRepository.fetchStocks
     * WHEN i invoke interactor
     * THEN i expect list of StockModel to be returned
     */
    @Test
    fun testRemoveFromFavorite() = runTest {
        val stocks: List<StockModel> = mockk()
        //given
        coEvery { muscleRepository.fetchStocks() } returns stocks

        //when
        getFavoriteStocksInteractor.get()

        //then
        coVerify { muscleRepository.fetchStocks() }
    }
}