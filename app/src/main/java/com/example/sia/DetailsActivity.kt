package com.example.sia

import android.os.Build
import android.os.Bundle
import android.transition.TransitionInflater
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.sia.models.Result
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
        var list = be!!.getSerializable("detail") as Result?
        if (list!!.media.size>0) {
            Glide.with(this).load(list?.media?.get(0)?.mediametadata?.get(2)?.url).into(image)
        }
        window.sharedElementEnterTransition = TransitionInflater.from(this).inflateTransition(R.transition.your_transition)
        image.transitionName="thumbnailTransition"
        title=list!!.title.toString()
        titleColor=R.color.white
        for (txt in list.des_facet) {
            TVtext.append(txt.toString())
        }
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