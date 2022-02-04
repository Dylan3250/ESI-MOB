package dylanbricar.android.pictureupload.screens.connection

import androidx.lifecycle.ViewModel
import dylanbricar.android.pictureupload.database.UserLogs
import dylanbricar.android.pictureupload.database.UserLogsRepository

class ConnectionViewModel(
    private val repository: UserLogsRepository
) : ViewModel() {

    fun saveConnection(givenEmail: String) {
        repository.insert(UserLogs(email = givenEmail))
    }
}