package com.app.stockapp.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.app.stockapp.ui.theme.space2
import com.app.stockapp.ui.theme.space4
import com.app.stockapp.ui.theme.space6

@Preview(showBackground = true)
@Composable
fun LoadingCardView() = Card {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(space4)
    ) {
        LoadingRectangleView(0.5f, space6)
        Spacer(modifier = Modifier.height(space4))
        LoadingRectangleView()
        Spacer(modifier = Modifier.height(space2))
        LoadingRectangleView()
    }
}


@Composable
fun LoadingRectangleView(width: Float = 1f, height: Dp = space4) = Spacer(
    modifier = Modifier
        .fillMaxWidth(fraction = width)
        .height(height)
        .background(
            color = MaterialTheme.colorScheme.surfaceContainerLow,
            shape = RoundedCornerShape(space6)
        )
)
