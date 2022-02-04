/*
 * Copyright 2019, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dylanbricar.android.pictureupload

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import dylanbricar.android.pictureupload.database.UserLogs
import dylanbricar.android.pictureupload.database.UserLogsDatabase
import dylanbricar.android.pictureupload.database.UserLogsDatabaseDao
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


/**
 * This is not meant to be a full set of tests. For simplicity, most of your samples do not
 * include tests. However, when building the Room, it is helpful to make sure it works before
 * adding the UI.
 */

@RunWith(AndroidJUnit4::class)
class UserLogsDatabaseTest {

    private lateinit var userLogsDao: UserLogsDatabaseDao
    private lateinit var db: UserLogsDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(context, UserLogsDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        userLogsDao = db.userLogsDatabaseDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun insertAndGetAllEquals() {
        userLogsDao.insert(UserLogs(email = "test@test.com"))
        assertEquals(
            "test@test.com",
            userLogsDao.getAll("test@test.com")[0].email
        )
    }
}