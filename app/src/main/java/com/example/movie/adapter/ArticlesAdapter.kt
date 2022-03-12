package com.example.movie.adapter

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movie.DetailsActivity
import com.example.movie.R
import com.example.movie.models.ResultsItem
import kotlinx.android.synthetic.main.articles_item.view.*


class ArticlesAdapter: RecyclerView.Adapter<ArticlesAdapter.ArticlesViewHolder>() {

    inner class ArticlesViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)

    private val diffCallback = object : DiffUtil.ItemCallback<ResultsItem>(){
        override fun areItemsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(list: List<ResultsItem>) = differ.submitList(list)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesViewHolder {
        return ArticlesViewHolder(
                LayoutInflater.from(
                        parent.context
                ).inflate(
                        R.layout.articles_item,
                        parent,
                        false
                )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBindViewHolder(holder: ArticlesViewHolder, position: Int) {

        val item = differ.currentList[position]
        var i=0

        holder.itemView.apply {
            tvTitle.text = "${item.title.toString()}"
            tvDescription.text="${item.overview}"
            Glide.with(this).load("http://image.tmdb.org/t/p/w185/"+item.poster_path).into(ivFood);

            tvDate.text="${item.release_date.toString()}"
            cardView.setOnClickListener {
                val detail = Intent(context, DetailsActivity::class.java)
                val be = Bundle()
                be.putSerializable("detail", item)
                detail.putExtras(be)
                ivFood.transitionName="thumbnailTransition"
                val pair1 = Pair.create<View, String>(ivFood as View?, ivFood!!.transitionName)
                val pair2 = Pair.create<View, String>(ivFood as View?, ivFood!!.transitionName)
                val optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation((context as Activity?)!!, pair1, pair2)
                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        (context as Activity)!!, ivFood, ViewCompat.getTransitionName(ivFood)!!)

                context.startActivity(detail,optionsCompat.toBundle())
            }
        }
    }
}