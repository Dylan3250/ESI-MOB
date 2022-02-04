package dylanbricar.android.pictureupload.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserLogsDatabaseDao {
    @Insert
    fun insert(item: UserLogs)

    @Query("SELECT id, email, lastDate FROM user_logs_table WHERE email = :email ORDER BY lastDate DESC")
    fun getAll(email: String): List<UserLogs>
}