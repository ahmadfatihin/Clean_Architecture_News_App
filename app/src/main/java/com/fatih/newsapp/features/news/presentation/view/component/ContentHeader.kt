package com.fatih.newsapp.features.news.presentation.view.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextOverflow
import com.fatih.newsapp.core.ui.Dimensions


@Composable
fun NewsHeader(
    author: String,
    publishedAt: String,
    maxLines: Int,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(
                vertical = dimensionResource(id = Dimensions.vertical_padding)
            ),
    ) {
        Icon(
            imageVector = Icons.Filled.AccountCircle,
            contentDescription = null,
            modifier = Modifier
                .size(
                    dimensionResource(id = Dimensions.header_icon_size)
                )
                .padding(
                    end = dimensionResource(id = Dimensions.header_icon_padding)
                )
        )

        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = author,
                style = MaterialTheme.typography.displayMedium,
                maxLines = maxLines,
                overflow = TextOverflow.Ellipsis,
            )

            Spacer(
                modifier = Modifier
                    .padding(
                        vertical = dimensionResource(id = Dimensions.header_spacer_padding))
            )

            Text(
                text = publishedAt,
                style = MaterialTheme.typography.labelLarge
            )
        }
    }

}