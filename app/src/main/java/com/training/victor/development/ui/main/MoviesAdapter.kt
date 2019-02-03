package com.training.victor.development.ui.main

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.training.victor.development.BuildConfig
import com.training.victor.development.R
import com.training.victor.development.data.Constants.Companion.IMAGE_SMALL
import com.training.victor.development.data.models.MovieItem
import com.training.victor.development.utils.inflate
import kotlinx.android.synthetic.main.adapter_profile_item.view.*

class MoviesAdapter(private val profilesList: ArrayList<MovieItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CreatorViewHolder(parent.inflate(R.layout.adapter_profile_item))
    }

    override fun getItemCount(): Int {
        return profilesList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CreatorViewHolder) {
            holder.bind(profilesList[position])
        }
    }

    class CreatorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: MovieItem) = with(itemView) {
            txtTitle.text = movie.title
            txtReleaseDate.text = movie.releaseDate
            txtOverView.text = movie.overview

            val imageUrl = BuildConfig.IMAGES_URL + IMAGE_SMALL + movie.posterPath
            Glide.with(itemView.context).load(imageUrl).into(imageMoviePoster)
        }
    }
}