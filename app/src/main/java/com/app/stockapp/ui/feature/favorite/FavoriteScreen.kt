package com.app.stockapp.ui.feature.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.stockapp.R
import com.app.commondomain.model.StockModel
import com.app.stockapp.ui.component.StockItemView
import com.app.stockapp.ui.theme.StockTheme
import com.app.stockapp.ui.theme.space4
import com.app.stockapp.ui.theme.space8
import kotlinx.coroutines.flow.flow

@Composable
fun FavoriteScreen() {
    val viewModel: FavoriteViewModel = hiltViewModel()
    val vmState by viewModel.uiState.collectAsStateWithLifecycle()
    FavoriteView(
        vmState = vmState,
        actionFavorite = viewModel::actionFavorite
    )
}

@PreviewLightDark
@Composable
fun FavoriteView(
    @PreviewParameter(FavoriteViewParameterProvider::class) vmState: FavoriteViewState,
    actionFavorite: (StockModel) -> Unit = {},
) {
    StockTheme {
        Column(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.background)
                .padding(horizontal = space4, vertical = space8)
        ) {
            when (vmState) {
                is FavoriteViewState.Content -> {
                    val items by vmState.stocks.collectAsStateWithLifecycle(emptyList())
                    LazyColumn(verticalArrangement = Arrangement.spacedBy(space4)) {
                        items(items) {
                            StockItemView(
                                model = it,
                                actionFavorite = { actionFavorite(it) }
                            )
                        }
                    }
                }

                FavoriteViewState.Empty -> FavoriteEmptyView()
            }
        }
    }
}

@Composable
fun FavoriteEmptyView() = Column(
    modifier = Modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
) {
    Icon(
        painter = painterResource(id = R.drawable.ic_favorite_border),
        contentDescription = "",
        tint = MaterialTheme.colorScheme.primary,
        modifier = Modifier.size(space8)
    )
    Spacer(modifier = Modifier.height(space8))
    Text(
        text = stringResource(R.string.favorite_empty_title),
        style = MaterialTheme.typography.headlineLarge,
        textAlign = TextAlign.Center
    )
}

class FavoriteViewParameterProvider : PreviewParameterProvider<FavoriteViewState> {
    override val values = sequenceOf(
        FavoriteViewState.Empty,
        FavoriteViewState.Content(
            flow {
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
            }
        )
    )
}
