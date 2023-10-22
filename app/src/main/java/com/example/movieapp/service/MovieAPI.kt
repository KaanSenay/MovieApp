package com.example.movieapp.service

import com.example.movieapp.model.MovieDetailModel
import com.example.movieapp.model.MovieModel
import com.example.movieapp.model.MovieResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {

    @GET("/")
    fun searchMovie(@Query("apikey") apiKey: String, @Query("s") movieName: String): Call<MovieResult>

    @GET("/")
    fun searchMovieDetails(@Query("apikey") apiKey: String, @Query("i") imdbID : String): Call<MovieDetailModel>
}