package com.app.commondomain.model

data class StockModel(
    val country: String,
    val currency: String,
    val exchange: String,
    val figiCode: String,
    val micCode: String,
    val name: String,
    val symbol: String,
    val type: String,
    val isFavorite: Boolean
)
