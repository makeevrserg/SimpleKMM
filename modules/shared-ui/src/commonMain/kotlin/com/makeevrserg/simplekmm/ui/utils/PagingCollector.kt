package com.makeevrserg.simplekmm.ui.utils

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.Composable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

fun LazyListState.isScrolledToTheEnd(): Boolean {
    val lastVisibleIndex = layoutInfo.visibleItemsInfo.lastOrNull()?.index
    val totalItems = layoutInfo.totalItemsCount - 1
    return lastVisibleIndex == totalItems || lastVisibleIndex == null || totalItems == 0
}

fun LazyGridState.isScrolledToTheEnd(): Boolean {
    val lastVisibleIndex = layoutInfo.visibleItemsInfo.lastOrNull()?.index
    val totalItems = layoutInfo.totalItemsCount - 1
    return lastVisibleIndex == totalItems || lastVisibleIndex == null || totalItems == 0
}


@Composable
fun <T> PagingCollector<T>.collect(state: LazyListState) {
    if (state.isScrolledToTheEnd())
        coroutineScope.launch(Dispatchers.IO) {

            loadNextPage()
        }

}

@Composable
fun <T> PagingCollector<T>.collect(state: LazyGridState) {
    if (state.isScrolledToTheEnd())
        coroutineScope.launch(Dispatchers.IO) {

            loadNextPage()
        }

}

class PagingCollector<T>(
    private val initialPage: Int,
    val coroutineScope: CoroutineScope,
    private val onUpdated: (List<T>) -> Unit,
    private val loader: suspend ( page: Int) -> List<T>?
) {
    var page = initialPage - 1
    private var isLastPage: Boolean = false
    private var isLoading: Boolean = false
    val list = MutableStateFlow<List<T>>(emptyList())
    suspend fun reset() {
        list.value = emptyList()
        isLastPage = false
        isLoading = false
        page = initialPage - 1
    }

    suspend fun loadNextPage() {
        if (isLastPage) return
        if (isLoading) return
        println("Loading page: $page")
        isLoading = true
        val nextPage = page + 1
        val nextList = loader(nextPage)
        if (!nextList.isNullOrEmpty())
            page = nextPage
        if (nextList != null && nextList.isEmpty())
            isLastPage = true
        list.update {
            it.toMutableList().apply {
                nextList?.let(::addAll)
            }
        }
        onUpdated(list.value)
        isLoading = false
    }
}