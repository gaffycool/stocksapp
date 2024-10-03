package com.app.stockapp.data.repository

import com.app.commondata.api.ApiService
import com.app.commondata.dto.StockDTO
import com.app.commondata.dto.StocksListResponseDTO
import com.app.commondata.repository.StocksRepositoryImpl
import com.app.commondata.room.StockEntity
import com.app.commondata.room.StocksDao
import com.app.commondomain.model.StockModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import retrofit2.Response

@ExperimentalCoroutinesApi
class StocksRepositoryImplTest {

    private val stocksDao: StocksDao = mockk()
    private val apiService: ApiService = mockk()
    private lateinit var repository: StocksRepositoryImpl

    val stockEntity = StockEntity(
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

    val stockModel = StockModel(
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
        repository = StocksRepositoryImpl(stocksDao, apiService)
    }

    /***
     * GIVEN apiService.fetchStocks returns success response
     * WHEN i invoke fetchStocks
     * THEN i expect list of StockModel to be returned
     */
    @Test
    fun testFetchMuscle() = runTest {
        //given
        val muscleResponseDTO = StocksListResponseDTO(
            data = listOf(
                StockDTO(
                    country = "country",
                    currency = "currency",
                    exchange = "exchange",
                    figi_code = "figi_code",
                    mic_code = "mic_code",
                    name = "name",
                    symbol = "symbol",
                    type = "type",
                )
            )
        )

        val response = Response.success(muscleResponseDTO)
        coEvery { apiService.fetchStocks(any()) } returns response
        coEvery { stocksDao.findWatchlist("symbol") } returns stockEntity

        //when
        val result = repository.fetchStocks()

        //then
        assertEquals(listOf(stockModel), result)
    }

    /***
     * GIVEN exerciseDao.getFavoriteStocks return live data
     * WHEN i invoke getFavoriteStocks
     * THEN i expect same live data to be returned
     */
    @Test
    fun testGetFavoriteStocks() = runTest {
        //given
        val liveData: Flow<List<StockEntity>> = flow { emit(listOf(stockEntity)) }
        coEvery { stocksDao.getFavoriteStocks() } returns liveData

        //when
        val result = repository.getFavoriteStocks()

        //then
        assertEquals(listOf(stockModel), result.first())
    }

    /***
     * GIVEN stocksDao.insert with StockModel
     * WHEN i invoke saveToWatchlist
     * THEN i expect StockModel to be saved
     */
    @Test
    fun testSaveToFavorite() = runTest {
        //given
        coEvery { stocksDao.insert(stockEntity) } just runs

        //when
        repository.saveToWatchlist(stockModel)

        //then
        coVerify { stocksDao.insert(stockEntity) }
    }

    /***
     * GIVEN stocksDao.delete with StockModel
     * WHEN i invoke removeFromFavorite
     * THEN i expect StockModel to be delete
     */
    @Test
    fun testRemoveFromFavorite() = runTest {
        //given
        coEvery { stocksDao.delete(stockEntity) } just runs

        //when
        repository.removeFromWatchlist(stockModel)

        //then
        coVerify { stocksDao.delete(stockEntity) }
    }
}
