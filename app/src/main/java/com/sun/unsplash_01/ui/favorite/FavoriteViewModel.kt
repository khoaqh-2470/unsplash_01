package com.sun.unsplash_01.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sun.unsplash_01.data.repository.PhotoRepository
import com.sun.unsplash_01.data.source.local.sqlite.entity.ImageLocal
import com.sun.unsplash_01.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class FavoriteViewModel(
    private val photoRepository: PhotoRepository
) : ViewModel() {


    private val _favoriteImageLiveData = MutableLiveData<Resource<List<ImageLocal>>>()
    val favoriteImageLiveData: LiveData<Resource<List<ImageLocal>>>
        get() = _favoriteImageLiveData

    fun getImages() {
        viewModelScope.launch(Dispatchers.IO) {
            _favoriteImageLiveData.postValue(Resource.loading(null))
            try {
                _favoriteImageLiveData.postValue(Resource.success(photoRepository.getImages()))
            } catch (ex: Exception) {
                _favoriteImageLiveData.postValue(Resource.error(null, ex.toString()))
            }
        }
    }
}
