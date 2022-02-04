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

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import dylanbricar.android.pictureupload.database.UserLogs
import dylanbricar.android.pictureupload.database.UserLogsDatabase
import dylanbricar.android.pictureupload.database.UserLogsRepository
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserLogsRepositoryTest {

    private lateinit var repo: UserLogsRepository

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val dao = UserLogsDatabase.getInstance(context).userLogsDatabaseDao
        repo = UserLogsRepository(dao)
    }

    @Test
    fun insertAndGetAllEquals() {
        repo.insert(UserLogs(email = "test@test.com"))
        assertEquals(
            "test@test.com",
            repo.getAll("test@test.com")[0].email
        )
    }
}