package com.example.movie

import android.os.Build
import android.os.Bundle
import android.transition.TransitionInflater
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.movie.models.ResultsItem
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.content_scrolling.*


class DetailsActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        setSupportActionBar(toolbar)
        val be = intent.extras
        var list = be!!.getSerializable("detail") as ResultsItem?
        Glide.with(this).load("http://image.tmdb.org/t/p/w185"+list?.poster_path).into(image)
        window.sharedElementEnterTransition = TransitionInflater.from(this).inflateTransition(R.transition.your_transition)
        image.transitionName="thumbnailTransition"
        title=list!!.title.toString()
        titleColor=R.color.white
        TVtext.append(list.overview)

        app_bar.addOnOffsetChangedListener(object : OnOffsetChangedListener {
            var isShow = false
            var scrollRange = -1
            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                }
                if (scrollRange + verticalOffset == 0) {
                    isShow = true
                } else if (isShow) {
                    isShow = false
                }
            }
        })
    }
}