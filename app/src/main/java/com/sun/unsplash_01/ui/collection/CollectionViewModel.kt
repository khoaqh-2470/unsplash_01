package com.sun.unsplash_01.ui.collection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sun.unsplash_01.data.model.Collection
import com.sun.unsplash_01.data.repository.PhotoRepository
import com.sun.unsplash_01.extensions.plusAssign
import com.sun.unsplash_01.utils.Constant.DEFAULT_PAGE
import com.sun.unsplash_01.utils.LoadMoreRecyclerViewListener
import com.sun.unsplash_01.utils.RefreshRecyclerViewListener
import com.sun.unsplash_01.utils.Resource
import kotlinx.coroutines.launch

class CollectionViewModel(
    private val photoRepository: PhotoRepository
) : ViewModel(),
    LoadMoreRecyclerViewListener,
    RefreshRecyclerViewListener {

    private var currentPosition = DEFAULT_PAGE
    private val _resource = MutableLiveData<Resource<LiveData<MutableList<Collection>>>>()
    val resource: LiveData<Resource<LiveData<MutableList<Collection>>>>
        get() = _resource

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _collections = MutableLiveData<MutableList<Collection>>(mutableListOf())
    val collections: LiveData<MutableList<Collection>>
        get() = _collections

    init {
        fetchCollections()
    }

    private fun fetchCollections() {
        viewModelScope.launch {
            try {
                _collections.plusAssign(photoRepository.getCollections(currentPosition))
                _resource.postValue(Resource.success(data = collections))
                currentPosition++
                _isLoading.value = false
            } catch (e: Exception) {
                _resource.postValue(Resource.error(data = null, message = e.message.toString()))
            }
        }
    }

    override fun onLoadData() {
        _isLoading.value = true
        fetchCollections()
    }

    override fun onRefresh() {
        _collections.value?.clear()
        currentPosition = DEFAULT_PAGE
        fetchCollections()
    }
}
