package com.sun.unsplash_01.data.repository

import com.sun.unsplash_01.data.source.remote.CollectionAPI
import com.sun.unsplash_01.utils.Constant.DEFAULT_PAGE

class CollectionRepository(private val client: CollectionAPI) {

    suspend fun getCollections(page: Int = DEFAULT_PAGE) = client.getCollections(page = page)

    suspend fun getPhotosCollection(
        id: String,
        page: Int = DEFAULT_PAGE
    ) = client.getPhotosCollection(id = id, page = page)
}
