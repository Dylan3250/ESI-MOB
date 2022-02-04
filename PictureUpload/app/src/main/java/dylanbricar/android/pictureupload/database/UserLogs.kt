package dylanbricar.android.pictureupload.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "user_logs_table")
data class UserLogs(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo(name = "email")
    @NotNull
    val email: String,

    @ColumnInfo(name = "lastDate")
    @NotNull
    val lastDate: Long = System.currentTimeMillis(),
)