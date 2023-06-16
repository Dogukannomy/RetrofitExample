package com.dogukandemir.retrofitexample

import retrofit2.Call
import retrofit2.http.GET

interface PhotosApi{

    @GET("photos")
    fun getData():Call<List<PhotosModel>>

}