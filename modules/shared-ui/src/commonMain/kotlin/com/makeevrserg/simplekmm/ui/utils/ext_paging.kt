package com.makeevrserg.simplekmm.ui.utils

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.Composable
import kotlinx.coroutines.Dispatchers
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
        coroutineScope.launch(Dispatchers.Unconfined) {

            loadNextPage()
        }

}

@Composable
fun <T> PagingCollector<T>.collect(state: LazyGridState) {
    if (state.isScrolledToTheEnd())
        coroutineScope.launch(Dispatchers.Unconfined) {

            loadNextPage()
        }

}