package com.app.stockapp.ui.feature.stocks

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.commondomain.model.StockModel
import com.app.stockapp.ui.component.StockItemView
import com.app.stockapp.ui.theme.StockTheme
import com.app.stockapp.ui.theme.space4
import com.app.stockapp.ui.theme.space8

@Composable
fun StocksScreen() {
    val viewModel: StocksViewModel = hiltViewModel()
    val vmState by viewModel.uiState.collectAsStateWithLifecycle()
    StocksView(
        vmState = vmState,
        actionFavorite = viewModel::actionFavorite,
    )
}

@PreviewLightDark
@Composable
fun StocksView(
    @PreviewParameter(StocksViewParameterProvider::class) vmState: StocksViewState,
    actionFavorite: (Int) -> Unit = {},
) {
    StockTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
                .padding(horizontal = space4)
        ) {
            Spacer(Modifier.height(space8))
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(space4)
            ) {
                items(count = vmState.stocks.count()) {
                    StockItemView(
                        model = vmState.stocks[it],
                        actionFavorite = { actionFavorite(it) }
                    )
                }
            }
        }
    }
}


class StocksViewParameterProvider : PreviewParameterProvider<StocksViewState> {
    override val values = sequenceOf(
        StocksViewState(
            listOf(
                StockModel(
                    symbol = "000",
                    name = "Greenvolt - Energias Energias Energias Renováveis, S.A.",
                    currency = "EUR",
                    exchange = "FSX",
                    micCode = "XFRA",
                    country = "Germany",
                    type = "Common Stock",
                    figiCode = "BBG011RFH095",
                    isFavorite = false
                ),
                StockModel(
                    symbol = "000",
                    name = "Greenvolt - Energias Renováveis, S.A.",
                    currency = "EUR",
                    exchange = "FSX",
                    micCode = "XFRA",
                    country = "Germany",
                    type = "Common Stock",
                    figiCode = "BBG011RFH095",
                    isFavorite = false
                )
            )
        )
    )
}
