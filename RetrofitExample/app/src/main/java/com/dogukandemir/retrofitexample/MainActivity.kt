package com.dogukandemir.retrofitexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.log

class MainActivity : AppCompatActivity(),Click {

    val BASE_URL = "https://jsonplaceholder.typicode.com/"
    val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
    var photosList =ArrayList<PhotosModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getPhotos()

    }
    fun getPhotos(){
        val service =retrofit.create(PhotosApi::class.java)
        val api =service.getData()

        api.enqueue(object :Callback<List<PhotosModel>>{
            override fun onResponse(call: Call<List<PhotosModel>>, response: Response<List<PhotosModel>>) {
               if (response.isSuccessful){
                   response.body()?.let {
                       photosList=ArrayList(it)
                   }
               //     Log.d("gelen veri",response.body()!!.get(0).url)
                   val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
                   recyclerView.layoutManager =LinearLayoutManager(this@MainActivity)
                   val adapter = PhotosAdapter(photosList,this@MainActivity)
                   recyclerView.adapter=adapter

               }
            }

            override fun onFailure(call: Call<List<PhotosModel>>, t: Throwable) {
                t.printStackTrace()
            }

        })




    }

    override fun click(position:Int) {
        Toast.makeText(this,"${position + 1}'a Tiklandim",Toast.LENGTH_LONG).show()
    }
}