package com.fatih.newsapp.features.news.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.fatih.newsapp.R
import com.fatih.newsapp.core.utils.Endpoint
import com.fatih.newsapp.features.news.data.paging.EverythingPagingSource
import com.fatih.newsapp.features.news.data.paging.HeadlinesPagingSource
import com.fatih.newsapp.features.news.domain.entity.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val everythingSource: EverythingPagingSource,
    private val headlinesSource: HeadlinesPagingSource
) : ViewModel() {

    private val _everythingPaging: MutableStateFlow<PagingData<Article>> =
        MutableStateFlow(PagingData.empty())

    private val _headlinesPaging: MutableStateFlow<PagingData<Article>> =
        MutableStateFlow(PagingData.empty())

    private val _state = MutableStateFlow(NewsViewState())
    var everythingPaging = _everythingPaging.asStateFlow()
        private set

    var headlinesPaging = _headlinesPaging.asStateFlow()
        private set

    val state = _state.asStateFlow()

    init {
        everythingPagingSource()
        headlinesPagingSource()
    }

    private fun everythingPagingSource() {
        viewModelScope.launch {
            Pager(
                config = PagingConfig(
                    7, enablePlaceholders = true
                )
            ) {
                everythingSource
            }.flow.cachedIn(viewModelScope).collect {
                _everythingPaging.value = it
            }

        }
    }

    private fun headlinesPagingSource() {
        viewModelScope.launch {
            Pager(
                config = PagingConfig(
                    7, enablePlaceholders = true
                )
            ) {
                headlinesSource
            }.flow.cachedIn(viewModelScope).collect {
                _headlinesPaging.value = it
            }
        }
    }

    fun toNews() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    endpoint = Endpoint.EVERYTHING,
                    newsIcon = R.drawable.solid_newspaper,
                    headlinesIcon = R.drawable.fire_regular
                )
            }
            Log.i("viewmodel-update-news", "state: ${_state.value.endpoint}")
        }
    }


    fun toHeadlines() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    endpoint = Endpoint.HEADLINES,
                    newsIcon = R.drawable.regular_newspaper,
                    headlinesIcon = R.drawable.fire_solid
                )
            }
            Log.i("viewmodel-update-article", "state: ${_state.value.endpoint}")
        }
    }

}