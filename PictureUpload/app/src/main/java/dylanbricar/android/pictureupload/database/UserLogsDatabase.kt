package dylanbricar.android.pictureupload.database

import android.content.Context

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

import kotlin.jvm.Volatile

@Database(entities = [UserLogs::class], version = 10, exportSchema = true)
abstract class UserLogsDatabase : RoomDatabase() {
    abstract val userLogsDatabaseDao: UserLogsDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: UserLogsDatabase? = null

        fun getInstance(context: Context): UserLogsDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UserLogsDatabase::class.java,
                        "userlogs_database"
                    ).fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}