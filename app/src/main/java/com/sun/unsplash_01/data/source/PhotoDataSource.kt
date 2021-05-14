package com.sun.unsplash_01.data.source

import com.sun.unsplash_01.data.model.Collection
import com.sun.unsplash_01.data.model.PhotoCollection
import com.sun.unsplash_01.data.model.Topic

class PhotoDataSource {

    interface Local {
    }

    interface Remote {
        suspend fun getCollections(page: Int): MutableList<Collection>

        suspend fun getPhotosCollection(id: String, page: Int): MutableList<PhotoCollection>

        suspend fun getTopics(page: Int): MutableList<Topic>

        suspend fun getRandomPhotos(): MutableList<PhotoCollection>
    }
}
