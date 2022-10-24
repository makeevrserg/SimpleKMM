package com.makeevrserg.simplekmm.ui.presentation.localdb_files

import com.makeevrserg.simplekmm.localb_api.ILocalDatabaseAPI
import com.makeevrserg.simplekmm.ui.core.BaseViewModel
import com.makeevrserg.simplekmm.ui.utils.PagingCollector
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.astrainteractive.astralearner.dto.FileDTO
import ru.astrainteractive.astralearner.dto.FileType
import ru.astrainteractive.astralearner.dto.ListFilter

class FilesViewModel(val api: ILocalDatabaseAPI) : BaseViewModel() {
    val collector = PagingCollector<FileDTO>(0, viewModelScope, onUpdated = {}, loader = { page ->
        api.getFiles(page, filter.value)?.data
    })
    val filter = MutableStateFlow(ListFilter(fileTypes = listOf(FileType.WEBM)))

    fun onRefreshClicked() = viewModelScope.launch(Dispatchers.IO) {
        collector.reset()
        collector.loadNextPage()
    }

    fun onFilterClicked(fileType: FileType) {
        filter.update {
            val types = it.fileTypes.toMutableList()
            if (types.contains(fileType))
                types.remove(fileType)
            else types.add(fileType)
            it.copy(fileTypes = types)
        }
    }

    init {
        viewModelScope.launch(Dispatchers.IO) { collector.reset() }
    }
}