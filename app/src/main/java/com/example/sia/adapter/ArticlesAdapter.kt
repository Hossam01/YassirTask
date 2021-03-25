package com.example.sia.adapter

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
import com.example.sia.DetailsActivity
import com.example.sia.R
import com.example.sia.models.Result
import kotlinx.android.synthetic.main.activity_details.view.*
import kotlinx.android.synthetic.main.articles_item.view.*


class ArticlesAdapter: RecyclerView.Adapter<ArticlesAdapter.ArticlesViewHolder>() {

    inner class ArticlesViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)

    private val diffCallback = object : DiffUtil.ItemCallback<Result>(){
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(list: List<Result>) = differ.submitList(list)

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
            tvDescription.text="${item.des_facet.get(0)}"
            if (item.media.size>0) {
                    Glide.with(this).load(item.media.get(0).mediametadata.get(2).url).into(ivFood);
            }

            tvDate.text="${item.published_date.toString()}"
            tvAuthor.text="${item.byline.toString()}"
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