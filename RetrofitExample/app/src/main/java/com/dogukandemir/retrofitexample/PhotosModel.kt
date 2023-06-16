package com.dogukandemir.retrofitexample

import android.net.Uri
import retrofit2.http.Url

data class PhotosModel(val albumId:Int,val id :Int,val title:String,val url:String)