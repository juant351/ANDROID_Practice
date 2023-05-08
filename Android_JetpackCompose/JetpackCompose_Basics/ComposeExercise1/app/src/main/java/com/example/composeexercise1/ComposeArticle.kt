package com.example.composeexercise1

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ComposeArticle(modifier: Modifier = Modifier) {
    Column (modifier = modifier) {
        // Header image
        Image(
            painter = painterResource(R.drawable.bg_compose_background),
            alignment = Alignment.TopCenter,
            contentDescription = null
        )
        ComposeArticleText()

    }
}

@Composable
fun ComposeArticleText(modifier: Modifier = Modifier) {
    Column (
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ){
        // Article title
        Text(
            text = stringResource(id = R.string.article_title),
            fontSize = 24.sp,
            modifier = Modifier.padding(16.dp)

        )
        Text(
            text = stringResource(id = R.string.article_paragraph_one),
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        )
        Text(
            text = stringResource(id = R.string.article_paragraph_two),
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview
@Composable
fun ComposeArticlePreview() {
    ComposeArticle()
}