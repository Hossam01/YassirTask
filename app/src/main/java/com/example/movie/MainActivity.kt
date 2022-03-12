package com.example.movie

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.movie.MainViewModel.ViewModelMain
import com.example.movie.adapter.ArticlesAdapter
import com.example.movie.models.ResultsItem
import com.example.movie.others.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint

class MainActivity : AppCompatActivity() {
    private val mainModel: ViewModelMain by viewModels()
    private lateinit var adapter: ArticlesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = ArticlesAdapter()
        rvArticleslist.adapter = adapter
        if (checkOnlineState()==true) {

            lifecycleScope.launchWhenStarted {
                mainModel.getUsers().collect {

                    when (it.status) {
                        Status.OK -> {
                            load.visibility = View.GONE
                            rvArticleslist.visibility = View.VISIBLE
                            it.results.let { res -> res?.results?.let { it1 -> adapter.submitList(
                                it1 as List<ResultsItem>
                            ) }
                            }
                        }
                        Status.LOADING -> {
                            load.visibility = View.VISIBLE
                            rvArticleslist.visibility = View.GONE
                        }
                        Status.ERROR -> {
                            load.visibility = View.GONE
                            rvArticleslist.visibility = View.VISIBLE
                            Toast.makeText(
                                this@MainActivity,
                                "Something went wrong",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }else{
            Toast.makeText(this,"Please The Connection and Try Again",Toast.LENGTH_SHORT).show()
        }
    }



    fun checkOnlineState(): Boolean {
        val CManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        val NInfo = CManager!!.activeNetworkInfo
        return if (NInfo != null && NInfo.isConnectedOrConnecting) {
            true
        } else false
    }
}