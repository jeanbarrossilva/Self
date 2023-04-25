package com.jeanbarrossilva.self.wheel.android.test

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.jeanbarrossilva.self.wheel.android.infra.AndroidWheelEditor
import com.jeanbarrossilva.self.wheel.android.infra.AndroidWheelRegister
import com.jeanbarrossilva.self.wheel.android.infra.AndroidWheelRepository
import com.jeanbarrossilva.self.wheel.android.infra.WheelDatabase
import com.jeanbarrossilva.self.wheel.core.test.WheelTestRule

fun WheelTestRule.Companion.android(): WheelTestRule {
    return object : WheelTestRule() {
        private lateinit var database: WheelDatabase

        override lateinit var repository: AndroidWheelRepository
            private set
        override lateinit var register: AndroidWheelRegister
            private set
        override lateinit var editor: AndroidWheelEditor
            private set

        override fun before() {
            val context = ApplicationProvider.getApplicationContext<Context>()
            database = Room.inMemoryDatabaseBuilder(context, WheelDatabase::class.java).build()
            val (wheelDao, areaDao, toDoDao) = with(database) { Triple(wheelDao, areaDao, toDoDao) }
            repository = AndroidWheelRepository(wheelDao, areaDao, toDoDao)
            register = AndroidWheelRegister(repository, wheelDao, areaDao, toDoDao)
            editor = AndroidWheelEditor(repository, wheelDao, areaDao, toDoDao)
        }

        override suspend fun onAfter() {
            database.clearAllTables()
        }
    }
}
