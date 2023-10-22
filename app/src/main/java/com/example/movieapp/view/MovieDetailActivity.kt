package com.example.movieapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.movieapp.databinding.ActivityMainDenemeBinding
import com.example.movieapp.model.MovieDetailModel
import com.example.movieapp.service.MovieAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainDenemeBinding
    private val BASE_URL = "http://www.omdbapi.com"
    private val API_KEY = "7f7561a3"
    private lateinit var imdbID : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainDenemeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        loadData()
    }

    private fun loadData() {
        val intent = intent
        imdbID = intent.getStringExtra("imdb").toString()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(MovieAPI::class.java)
        val call = service.searchMovieDetails(API_KEY,imdbID)
        call.enqueue(object : Callback<MovieDetailModel> {
            override fun onResponse(
                call: Call<MovieDetailModel>,
                response: Response<MovieDetailModel>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        binding.movieName.text = it.Title
                        binding.movieDesc.text = it.Plot
                        binding.year.text = it.Year
                        binding.runtime.text = it.Runtime
                        binding.genre.text = it.Genre
                        binding.actors.text = it.Actors
                        binding.awards.text = it.Awards
                        Glide.with(this@MovieDetailActivity)
                            .load(it.Poster)
                            .centerInside()
                            .into(binding.imageView)
                    }}
                else {
                    println("Yanit başarisiz: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<MovieDetailModel>, t: Throwable) {
                println("Ag istegi basarisiz: ${t.message}")
            }
        })
    }

}