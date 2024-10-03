package com.app.commondata.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.commondomain.model.StockModel

@Entity(tableName = "stocks")
data class StockEntity(
    val country: String,
    val currency: String,
    val exchange: String,
    val figiCode: String,
    val micCode: String,
    val name: String,
    @PrimaryKey val symbol: String,
    val type: String,
    val isFavorite: Boolean = false
) {
    fun transform() = StockModel(
        country, currency, exchange, figiCode, micCode, name, symbol, type, isFavorite
    )

    constructor(stockModel: StockModel) : this(
        country = stockModel.country,
        currency = stockModel.currency,
        exchange = stockModel.exchange,
        figiCode = stockModel.figiCode,
        micCode = stockModel.micCode,
        name = stockModel.name,
        symbol = stockModel.symbol,
        type = stockModel.type,
        isFavorite = stockModel.isFavorite
    )
}
