package com.makeevrserg.simplekmm.ui.presentation.localdb_files

import com.makeevrserg.simplekmm.localb_api.ILocalDatabaseAPI
import com.makeevrserg.simplekmm.ui.BaseViewModel
import com.makeevrserg.simplekmm.ui.utils.PagingCollector
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.astrainteractive.astralearner.dto.FileDTO

class FilesViewModel(val api: ILocalDatabaseAPI) : BaseViewModel() {
    val collector = PagingCollector<FileDTO>(10, 0, viewModelScope, onUpdated = {}, loader = { _, page ->
        api.getFiles(page).data
    })
    val files: MutableStateFlow<List<FileDTO>>
        get() = collector.list

    init {
        viewModelScope.launch(Dispatchers.IO) { collector.reset() }
    }
}