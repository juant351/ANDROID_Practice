package com.example.composeexercise1

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeexercise1.ui.theme.Color1
import com.example.composeexercise1.ui.theme.Color2
import com.example.composeexercise1.ui.theme.Color3
import com.example.composeexercise1.ui.theme.Color4
import com.example.composeexercise1.ui.theme.Purple40

@Composable
fun ComposeQuadrant(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(Modifier.weight(1f)) {
            Quadrant(
                textComposable = stringResource(id = R.string.text_composable),
                textContent = stringResource(id = R.string.text_composable_content),
                backgroundColor = Color1,
                modifier = Modifier.weight(1f),
            )
            Quadrant(
                textComposable = stringResource(id = R.string.image_composable),
                textContent = stringResource(id = R.string.image_composable_content),
                backgroundColor = Color2,
                modifier = Modifier.weight(1f),
            )
        }
        Row(Modifier.weight(1f)) {
            Quadrant(
                textComposable = stringResource(id = R.string.row_composable),
                textContent = stringResource(id = R.string.row_composable_content),
                backgroundColor = Color3,
                modifier = Modifier.weight(1f),
            )
            Quadrant(
                textComposable = stringResource(id = R.string.column_composable),
                textContent = stringResource(id = R.string.column_composable_content),
                backgroundColor = Color4,
                modifier = Modifier.weight(1f),
            )
        }

    }
}

@Composable
private fun Quadrant(
    textComposable: String,
    textContent: String,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp)
    ) {
        Text(
            text = textComposable,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = textContent,
            textAlign = TextAlign.Justify
        )
    }
}

@Preview
@Composable
fun ComposeQuadrantPreview() {
    ComposeQuadrant()
}