package dylanbricar.android.pictureupload.screens.connection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dylanbricar.android.pictureupload.database.UserLogsRepository

class ConnectionViewModelFactory(
    private val dataSource: UserLogsRepository
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ConnectionViewModel::class.java)) {
            return ConnectionViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
