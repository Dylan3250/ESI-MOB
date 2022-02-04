package dylanbricar.android.pictureupload.database

class UserLogsRepository(private val userLogsDatabaseDao: UserLogsDatabaseDao) {

    fun insert(item: UserLogs) {
        userLogsDatabaseDao.insert(item)
    }

    fun getAll(email: String): List<UserLogs> {
        return userLogsDatabaseDao.getAll(email)
    }
}