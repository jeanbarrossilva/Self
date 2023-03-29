package com.jeanbarrossilva.self.wheel.android.infra

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jeanbarrossilva.self.wheel.android.domain.area.AndroidAreaDao
import com.jeanbarrossilva.self.wheel.android.domain.area.AndroidAreaEntity
import com.jeanbarrossilva.self.wheel.android.domain.todo.AndroidToDoDao
import com.jeanbarrossilva.self.wheel.android.domain.todo.AndroidToDoEntity
import com.jeanbarrossilva.self.wheel.android.domain.wheel.AndroidWheelDao
import com.jeanbarrossilva.self.wheel.android.domain.wheel.AndroidWheelEntity

@Database(
    entities = [AndroidWheelEntity::class, AndroidAreaEntity::class, AndroidToDoEntity::class],
    version = 1
)
abstract class WheelDatabase internal constructor() : RoomDatabase() {
    abstract val wheelDao: AndroidWheelDao
    abstract val areaDao: AndroidAreaDao
    abstract val toDoDao: AndroidToDoDao

    companion object {
        private lateinit var instance: WheelDatabase

        internal fun get(context: Context): WheelDatabase {
            if (!::instance.isInitialized) {
                instance = createInstance(context)
            }
            return instance
        }

        private fun createInstance(context: Context): WheelDatabase {
            return Room.databaseBuilder(context, WheelDatabase::class.java, "wheel-db").build()
        }
    }
}
