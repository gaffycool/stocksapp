package com.app.commondomain.interactors

import com.app.commondomain.model.StockModel
import com.app.commondomain.repository.StocksRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetFavoriteStocksInteractorTest {

    private val muscleRepository: StocksRepository = mockk()
    private lateinit var getFavoriteStocksInteractor: GetFavoriteStocksInteractor

    @Before
    fun setUp() {
        getFavoriteStocksInteractor = GetFavoriteStocksInteractor(muscleRepository)
    }

    /***
     * GIVEN muscleRepository.getFavoriteStocks
     * WHEN i invoke interactor
     * THEN i expect flow of StockModel to be returned
     */
    @Test
    fun testRemoveFromFavorite() = runTest {
        val flow = flow<List<StockModel>> { }
        //given
        coEvery { muscleRepository.getFavoriteStocks() } returns flow

        //when
        getFavoriteStocksInteractor.get()

        //then
        coVerify { muscleRepository.getFavoriteStocks() }
    }
}