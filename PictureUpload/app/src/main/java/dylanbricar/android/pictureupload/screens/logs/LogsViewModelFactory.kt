package dylanbricar.android.pictureupload.screens.logs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dylanbricar.android.pictureupload.database.UserLogsRepository

class LogsViewModelFactory(
    private val dataSource: UserLogsRepository
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LogsViewModel::class.java)) {
            return LogsViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
