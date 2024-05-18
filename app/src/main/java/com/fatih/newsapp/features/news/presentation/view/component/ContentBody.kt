package com.fatih.newsapp.features.news.presentation.view.component

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.fatih.newsapp.R
import com.fatih.newsapp.core.ui.Dimensions

@Composable
fun NewsImage(
    modifier: Modifier = Modifier,
    imageUrl: String?
) {
    Box(
        modifier = modifier
            .height(200.dp)
            .fillMaxWidth()
            .padding(
                vertical = dimensionResource(id = R.dimen.vertical_padding)
            )
            .clip(MaterialTheme.shapes.large),
        contentAlignment = Alignment.Center,
    ) {
        Log.i("image-url", imageUrl ?: "null")
        if (imageUrl != null) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(MaterialTheme.shapes.large)
            )

        } else {
            Image(
                painter = painterResource(id = R.drawable.headlines),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxSize()
            )
        }
    }
}

@Composable
fun NewsTitle(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        modifier = modifier
            .padding(
                vertical = dimensionResource(id = R.dimen.vertical_padding))
    )
}

@Composable
fun NewsDescription(
    newsDesc: String?,
    maxLines: Int,
    modifier: Modifier = Modifier
) {
    if(newsDesc != null)
        Text(
            text = newsDesc,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = maxLines,
            overflow = TextOverflow.Ellipsis,
            modifier = modifier
                .padding(
                    vertical = dimensionResource(id = R.dimen.vertical_padding))
        )
}