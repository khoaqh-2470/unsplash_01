package com.sun.unsplash_01.data.source.remote

import com.sun.unsplash_01.data.model.Collection
import com.sun.unsplash_01.data.model.PhotoCollection
import com.sun.unsplash_01.utils.Constant.API_KEY
import com.sun.unsplash_01.utils.Constant.DEFAULT_PAGE
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CollectionAPI {

    @GET("/collections")
    suspend fun getCollections(
        @Query("client_id") keyApi: String = API_KEY,
        @Query("page") page: Int = DEFAULT_PAGE
    ): MutableList<Collection>

    @GET("/collections/{id}/photos")
    suspend fun getPhotosCollection(
        @Path("id") id: String,
        @Query("client_id") keyApi: String = API_KEY,
        @Query("page") page: Int = DEFAULT_PAGE
    ): MutableList<PhotoCollection>
}
