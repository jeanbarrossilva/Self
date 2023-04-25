package com.jeanbarrossilva.self.wheel.android.test

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.jeanbarrossilva.self.wheel.android.infra.AndroidWheelEditor
import com.jeanbarrossilva.self.wheel.android.infra.AndroidWheelRegister
import com.jeanbarrossilva.self.wheel.android.infra.AndroidWheelRepository
import com.jeanbarrossilva.self.wheel.android.infra.WheelDatabase
import org.junit.rules.ExternalResource

class AndroidWheelTestRule : ExternalResource() {
    private lateinit var database: WheelDatabase

    lateinit var repository: AndroidWheelRepository
        private set
    lateinit var register: AndroidWheelRegister
        private set
    lateinit var editor: AndroidWheelEditor
        private set

    override fun before() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, WheelDatabase::class.java).build()
        val (wheelDao, areaDao, toDoDao) = with(database) { Triple(wheelDao, areaDao, toDoDao) }
        repository = AndroidWheelRepository(wheelDao, areaDao, toDoDao)
        register = AndroidWheelRegister(repository, wheelDao, areaDao, toDoDao)
        editor = AndroidWheelEditor(repository, wheelDao, areaDao, toDoDao)
    }

    override fun after() {
        database.clearAllTables()
    }
}
