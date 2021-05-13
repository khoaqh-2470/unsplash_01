package com.sun.unsplash_01.data.source.local.sqlite.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.sun.unsplash_01.data.source.local.sqlite.entity.ImageLocal

@Dao
interface ImageDao {

    @Query("SELECT * FROM imageLocal")
    suspend fun getImages(): List<ImageLocal>

    @Insert
    suspend fun insertImage(imageLocal: ImageLocal)

    @Delete
    suspend fun deleteImage(imageLocal: ImageLocal)

    @Query("SELECT * FROM imageLocal WHERE id LIKE :imageId")
    suspend fun getImage(imageId: String?): ImageLocal
}
