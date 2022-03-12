package com.example.movie.MainViewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.models.MovieDiscoverModel
import com.example.movie.repositry.MainRepository
import com.example.movie.others.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ViewModelMain @ViewModelInject
constructor(
    private val mainRepository: MainRepository
): ViewModel(){

    private val _res = MutableStateFlow<Resource<MovieDiscoverModel>>(Resource.loading(null))


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
    fun getUsers(): StateFlow<Resource<MovieDiscoverModel>> {
        return _res
    }


}