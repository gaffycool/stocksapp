package com.app.stockapp.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.app.stockapp.R
import com.app.commondomain.model.StockModel
import com.app.stockapp.ui.theme.space2
import com.app.stockapp.ui.theme.space4
import com.app.stockapp.ui.theme.space6

@Composable
fun StockItemView(
    model: StockModel,
    actionFavorite: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    Card(modifier = Modifier.clickable { expanded = !expanded }) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(space4)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = model.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                Spacer(Modifier.width(space4))
                IconButton(
                    onClick = actionFavorite,
                    modifier = Modifier.size(space6)
                ) {
                    Icon(
                        painter = painterResource(
                            id = if (model.isFavorite) R.drawable.ic_favorite
                            else R.drawable.ic_favorite_border
                        ),
                        contentDescription = "Favorite",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
            Text(
                text = "${model.currency} - ${model.country}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(Modifier.height(space2))
            Text(
                text = model.symbol,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Preview
@Composable
fun StockItemPreview() {
    StockItemView(
        model = StockModel(
            symbol = "000",
            name = "Greenvolt - Energias Energias Energias Renováveis, S.A.",
            currency = "EUR",
            exchange = "FSX",
            micCode = "XFRA",
            country = "Germany",
            type = "Common Stock",
            figiCode = "BBG011RFH095",
            isFavorite = true
        ),
        actionFavorite = {}
    )
}
