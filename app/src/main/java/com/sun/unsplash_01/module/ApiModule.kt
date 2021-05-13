package com.sun.unsplash_01.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sun.unsplash_01.data.source.remote.CollectionAPI
import com.sun.unsplash_01.data.source.remote.RetrofitClient
import com.sun.unsplash_01.utils.Constant.BASE_URL
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val apiModule = module {

    fun provideCollectionApi(retrofit: RetrofitClient) = retrofit.create(CollectionAPI::class.java)

    single { provideCollectionApi(get()) }
}

val retrofitModule = module {

    fun provideGson() = GsonBuilder().create()

    fun provideGsonConverterFactory(factory: Gson) = GsonConverterFactory.create(factory)

    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .build()
    }

    fun provideClient(retrofit: Retrofit): RetrofitClient {
        return RetrofitClient(retrofit)
    }

    fun provideRetrofit(factory: GsonConverterFactory, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(factory)
            .client(client)
            .build()
    }

    single { provideGson() }
    single { provideHttpClient() }
    single { provideGsonConverterFactory(get()) }
    single { provideRetrofit(get(), get()) }
    single { provideClient(get()) }
}
