package com.sun.unsplash_01.module

import android.content.Context
import com.sun.unsplash_01.data.source.local.sqlite.database.DatabaseBuilder
import org.koin.dsl.module

val storageModule = module {

    fun buildRoomDB(context: Context) = DatabaseBuilder().buildRoomDB(context)

    single { buildRoomDB(get()) }
}
