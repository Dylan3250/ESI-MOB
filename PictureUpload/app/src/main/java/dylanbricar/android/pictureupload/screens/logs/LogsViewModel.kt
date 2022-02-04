package dylanbricar.android.pictureupload.screens.logs

import androidx.lifecycle.ViewModel
import dylanbricar.android.pictureupload.database.UserLogsRepository
import dylanbricar.android.pictureupload.formatHTMLUsers

class LogsViewModel(
    private val repository: UserLogsRepository
) : ViewModel() {

    private var _allUsers = ""
    val allUsers: String
        get() = _allUsers

    fun loadDatas(email: String) {
        _allUsers = formatHTMLUsers(repository.getAll(email)).toString()
    }
}