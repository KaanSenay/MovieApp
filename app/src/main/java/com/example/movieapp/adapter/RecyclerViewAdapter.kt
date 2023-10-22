package com.example.movieapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.model.MovieModel
import com.example.movieapp.view.MovieDetailActivity

class RecyclerViewAdapter(private val movieList : List<MovieModel>) : RecyclerView.Adapter<RecyclerViewAdapter.RowHolder>() {

    class RowHolder(view : View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = itemView.findViewById(R.id.text_movietitle)
        val yearTextView: TextView = itemView.findViewById(R.id.text_movieyear)
        val typeTextView: TextView = itemView.findViewById(R.id.text_movietype)
        val imdbTextView: TextView = itemView.findViewById(R.id.text_imdbID)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_layout,parent,false)
        return RowHolder(view)
    }

    override fun getItemCount(): Int {
        return movieList.count()
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        val currentItem = movieList[position]
        holder.titleTextView.text = currentItem.Title
        holder.yearTextView.text = currentItem.Year
        holder.typeTextView.text = currentItem.Type
        holder.imdbTextView.text = currentItem.imdbID
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context,MovieDetailActivity::class.java)
            intent.putExtra("imdb",currentItem.imdbID)
            holder.itemView.context.startActivity(intent)
        }
    }

}