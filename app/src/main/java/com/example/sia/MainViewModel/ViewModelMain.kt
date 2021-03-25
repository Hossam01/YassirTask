package com.example.sia.MainViewModel

import android.content.Context
import android.net.ConnectivityManager
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sia.repositry.MainRepository
import com.example.sia.models.Articles
import com.example.sia.others.Resource
import com.google.android.material.internal.ContextUtils.getActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ViewModelMain @ViewModelInject constructor(
    private val mainRepository: MainRepository
): ViewModel(){

    private val _res = MutableStateFlow<Resource<Articles>>(Resource.loading(null))


    init {
        getData()
    }

    private fun getData()  = viewModelScope.launch {
        _res.emit(Resource.loading(null))
        mainRepository.getArticales().let {
            if (it.isSuccessful){
                _res.emit(Resource.success(it.body()))
            }else{
                _res.emit(Resource.error(it.errorBody().toString(), null))
            }
        }

    }

    @ExperimentalCoroutinesApi
    fun getUsers(): StateFlow<Resource<Articles>> {
        return _res
    }


}