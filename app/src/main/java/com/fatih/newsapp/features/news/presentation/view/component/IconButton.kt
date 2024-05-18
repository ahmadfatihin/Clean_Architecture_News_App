package com.fatih.newsapp.features.news.presentation.view.component

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.focusTarget
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import com.fatih.newsapp.R
import com.fatih.newsapp.core.ui.Dimensions

@Composable
fun BottomBarButton(
    focusRequester: FocusRequester,
    icon: Int,
    modifier: Modifier = Modifier, onClick: () -> Unit
) {
    IconButton(onClick = {
        onClick()
        focusRequester.requestFocus()
    },
        modifier = modifier
            .focusRequester(focusRequester)
            .focusTarget()
    ) {
        Icon(
            painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier
                .size(
                    dimensionResource(id = R.dimen.icon_button_size)
                )
        )
    }
}