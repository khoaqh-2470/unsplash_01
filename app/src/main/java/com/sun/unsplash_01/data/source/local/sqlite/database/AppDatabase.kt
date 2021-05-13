package com.sun.unsplash_01.data.source.local.sqlite.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sun.unsplash_01.data.source.local.sqlite.dao.ImageDao
import com.sun.unsplash_01.data.source.local.sqlite.entity.ImageLocal

@Database(entities = [ImageLocal::class], version = 1)
abstract class AppDatabase : RoomDatabase(){

    abstract val imageDao: ImageDao
}
