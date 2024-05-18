package com.fatih.newsapp.features.news.presentation.view

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.paging.compose.LazyPagingItems
import com.fatih.newsapp.R
import com.fatih.newsapp.core.utils.Endpoint
import com.fatih.newsapp.features.news.domain.entity.Article
import com.fatih.newsapp.features.news.presentation.viewmodel.NewsViewState
import com.fatih.newsapp.features.news.presentation.view.component.ArticleLazyColumn
import com.fatih.newsapp.features.news.presentation.view.component.BottomBarButton
import com.fatih.newsapp.features.news.presentation.view.component.LoadingAnimation

@Composable
fun NewsContent(
    newsPagingData: LazyPagingItems<Article>,
    headlinesPagingData: LazyPagingItems<Article>,
    state: NewsViewState,
    newsOnClick: () -> Unit,
    headlinesOnClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = Modifier.navigationBarsPadding(),
        bottomBar = {
            BottomAppBar(
                modifier = modifier
                    .height(
                        dimensionResource(id = R.dimen.bottom_app_bar_height)
                    )
                    .padding(
                        dimensionResource(id = R.dimen.bottom_app_bar_padding)
                    )
                ,
                actions = {
                    BottomBarButton(
                        focusRequester = state.newsFocus,
                        icon = state.newsIcon,
                        modifier = Modifier
                            .weight(1f),
                        onClick = newsOnClick
                    )
                    BottomBarButton(
                        focusRequester = state.headlinesFocus,
                        icon = state.headlinesIcon,
                        modifier = Modifier
                            .weight(1f),
                        onClick = headlinesOnClick
                    )
                }
            )
        }
    ) {
        when(state.endpoint) {
            Endpoint.EVERYTHING -> {
                LoadingAnimation(pagingData = newsPagingData)
                ArticleLazyColumn(paddingValues = it, articles = newsPagingData)
            }
            else -> {
                LoadingAnimation(pagingData = headlinesPagingData)
                ArticleLazyColumn(paddingValues = it, articles = headlinesPagingData)
            }
        }
    }
}
