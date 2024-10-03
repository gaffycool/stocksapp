package com.app.commondata.dto

import com.app.commondomain.model.StockModel
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StocksListResponseDTO(
    val data: List<StockDTO>?
)

@JsonClass(generateAdapter = true)
data class StockDTO(
    val country: String,
    val currency: String,
    val exchange: String,
    val figi_code: String,
    val mic_code: String,
    val name: String,
    val symbol: String,
    val type: String
) {
    fun toDomainModel(isFavorite: Boolean) = com.app.commondomain.model.StockModel(
        country = country,
        currency = currency,
        exchange = exchange,
        figiCode = figi_code,
        micCode = mic_code,
        name = name,
        symbol = symbol,
        type = type,
        isFavorite = isFavorite,
    )
}
