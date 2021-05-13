package com.sun.unsplash_01.data.source.local.sqlite.database

import android.content.Context
import androidx.room.Room
import com.sun.unsplash_01.utils.Constant

class DatabaseBuilder {

    fun buildRoomDB(context: Context) =
        Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            Constant.DATABASE_NAME
        ).build()
}
