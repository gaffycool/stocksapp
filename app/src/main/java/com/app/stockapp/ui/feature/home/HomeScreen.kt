package com.app.stockapp.ui.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.stockapp.ui.StockNavigationActions
import com.app.stockapp.ui.feature.favorite.FavoriteScreen
import com.app.stockapp.ui.feature.home.HomeTabItem.Stocks
import com.app.stockapp.ui.feature.home.HomeTabItem.Watchlist
import com.app.stockapp.ui.feature.stocks.StocksScreen
import kotlinx.coroutines.launch


@Composable
fun HomeScreen(navigationActions: StockNavigationActions) {
    val viewModel: HomeViewModel = hiltViewModel()
    val vmState by viewModel.uiState.collectAsStateWithLifecycle()
    HomeView(
        vmState = vmState,
        onPageChange = viewModel::onPageChange
    )
}

@PreviewLightDark
@Composable
fun HomeView(
    @PreviewParameter(HomeViewParameterProvider::class) vmState: HomeViewState,
    onPageChange: (Int) -> Unit = {},
) {
    Column(
        modifier = Modifier.background(color = MaterialTheme.colorScheme.background)
    ) {
        val coroutineScope = rememberCoroutineScope()
        val pagerState = rememberPagerState(pageCount = { vmState.tabs.size })
        LaunchedEffect(pagerState.currentPage) {
            onPageChange(pagerState.currentPage)
        }
        HorizontalPager(
            state = pagerState,
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { index ->
            when (vmState.tabs[index]) {
                Stocks -> StocksScreen()
                Watchlist -> FavoriteScreen()
            }
        }

        TabRow(selectedTabIndex = pagerState.currentPage) {
            vmState.tabs.forEachIndexed { index, page ->
                val title = stringResource(id = page.title)
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
                    text = {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.labelMedium
                        )
                    },
                    unselectedContentColor = MaterialTheme.colorScheme.secondary,
                    selectedContentColor = MaterialTheme.colorScheme.primary,
                )
            }
        }
    }
}

class HomeViewParameterProvider : PreviewParameterProvider<HomeViewState> {
    override val values = sequenceOf(
        HomeViewState()
    )
}
