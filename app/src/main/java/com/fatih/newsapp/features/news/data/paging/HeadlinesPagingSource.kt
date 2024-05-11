package com.fatih.newsapp.features.news.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import arrow.core.Either
import com.fatih.newsapp.core.utils.Event
import com.fatih.newsapp.core.utils.sendEvent
import com.fatih.newsapp.features.news.domain.entity.Article
import com.fatih.newsapp.features.news.domain.repository.NewsRepository
import com.fatih.newsapp.features.news.domain.entity.request.TopHeadlinesEndpointParam
import kotlinx.coroutines.delay
import javax.inject.Inject

class HeadlinesPagingSource@Inject constructor(
    private val repository: NewsRepository,
) : PagingSource<Int, Article>() {

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        delay(3000) // emit load state
        val page = params.key ?: 1
        val param = TopHeadlinesEndpointParam(
            country = "id",
            pageSize = params.loadSize,
            page = page
        )

        Log.i("api-paging-headlines-source-page", "page: $page")

        return when(val response = repository.getHeadlines(param.toHashMap())) {
            is Either.Left -> {
                response.onLeft {
                    sendEvent(Event.ToastMessage(it.error.message))
                }
                // pass Throwable to play safe
                LoadResult.Error(response.leftOrNull()?.t ?: Throwable(message = "Unknown Error"))
            }
            is Either.Right -> {
                var data: List<Article> = emptyList()

                response.onRight {
                    data = it.articles
                    Log.i("api-paging-news-headlines-status", "status: ${it.status}")
                }

                LoadResult.Page(
                    data = data,
                    prevKey = if (page == 1) null else page.minus(1),
                    nextKey = if (data.isEmpty()) null else page.plus(1),
                )
            }
        }
    }
}