package com.example.movieapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.movieapp.R
import com.example.movieapp.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    fun nextActivity(view: View) {
        val movieName = binding.editTextText.text.toString()
        val intent = Intent(this,MainActivity::class.java)
        intent.putExtra("movieName",movieName)
        startActivity(intent)
    }

}