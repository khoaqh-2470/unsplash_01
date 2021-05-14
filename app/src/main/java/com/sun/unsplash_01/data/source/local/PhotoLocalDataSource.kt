package com.sun.unsplash_01.data.source.local

import com.sun.unsplash_01.data.source.PhotoDataSource
import com.sun.unsplash_01.data.source.local.sqlite.dao.ImageDao
import com.sun.unsplash_01.data.source.local.sqlite.entity.ImageLocal

class PhotoLocalDataSource(private val imageDao: ImageDao) : PhotoDataSource.Local {

    override suspend fun getImages(): List<ImageLocal> =
        imageDao.getImages()

    override suspend fun insertImage(imageLocal: ImageLocal) =
        imageDao.insertImage(imageLocal)

    override suspend fun deleteImage(imageLocal: ImageLocal) =
        imageDao.deleteImage(imageLocal)

    override suspend fun getImage(imageId: String?): ImageLocal =
        imageDao.getImage(imageId)
}
