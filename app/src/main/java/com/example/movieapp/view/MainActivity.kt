package com.example.movieapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.adapter.RecyclerViewAdapter
import com.example.movieapp.databinding.ActivityMainBinding
import com.example.movieapp.model.MovieResult
import com.example.movieapp.service.MovieAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val BASE_URL = "http://www.omdbapi.com"
    private val API_KEY = "7f7561a3"
    private lateinit var movieName : String
    private var recyclerViewAdapter : RecyclerViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        loadData()
    }

    private fun loadData() {

        movieName = intent.getStringExtra("movieName").toString()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(MovieAPI::class.java)
        val call = service.searchMovie(API_KEY,movieName)
        call.enqueue(object : Callback<MovieResult> {
            override fun onResponse(
                call: Call<MovieResult>,
                response: Response<MovieResult>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        val movieResult = response.body()
                        if (movieResult != null) {
                            val movieModels = movieResult.Search
                            recyclerViewAdapter = RecyclerViewAdapter(movieModels)
                            binding.recyclerView.adapter = recyclerViewAdapter

                        } else {
                            println("JSON yaniti bos.")
                        }

                    }}
                else {
                    println("Yanit başarisiz: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<MovieResult>, t: Throwable) {
                println("Ag istegi basarisiz: ${t.message}")
            }
        })
    }

}