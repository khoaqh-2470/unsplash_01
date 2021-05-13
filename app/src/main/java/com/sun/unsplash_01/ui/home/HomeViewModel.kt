package com.sun.unsplash_01.ui.home

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.*
import com.sun.unsplash_01.data.model.Image
import com.sun.unsplash_01.data.model.PhotoCollection
import com.sun.unsplash_01.data.model.Topic
import com.sun.unsplash_01.data.repository.PhotoRepository
import com.sun.unsplash_01.extensions.plusAssign
import com.sun.unsplash_01.utils.Constant.DEFAULT_ITEM
import com.sun.unsplash_01.utils.Constant.DEFAULT_PAGE
import com.sun.unsplash_01.utils.LoadMoreRecyclerViewListener
import com.sun.unsplash_01.utils.RefreshRecyclerViewListener
import com.sun.unsplash_01.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val photoRepository: PhotoRepository) : ViewModel(),
    LoadMoreRecyclerViewListener,
    RefreshRecyclerViewListener {

    private var currentPosition = DEFAULT_PAGE

    private val _resource = MutableLiveData<Resource<LiveData<MutableList<Topic>>>>()
    val resource: LiveData<Resource<LiveData<MutableList<Topic>>>>
        get() = _resource

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _topics = MutableLiveData<MutableList<Topic>>(mutableListOf())
    val topics: LiveData<MutableList<Topic>>
        get() = _topics

    private val _photos = MutableLiveData<MutableList<PhotoCollection>>(mutableListOf())
    val photos: LiveData<MutableList<PhotoCollection>>
        get() = _photos

    init {
        fetchTopics()
        fetchRandomPhoto()
    }

    private fun fetchRandomPhoto() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _photos.plusAssign(photoRepository.getRandomPhotos())
            } catch (e: Exception) {
            }
        }
    }

    private fun fetchTopics() {
        viewModelScope.launch {
            try {
                photoRepository.getTopics(currentPosition).let { listTopic ->
                    if (listTopic.size == 0) {
                        removeItemLoad()
                        _isLoading.value = true
                        return@launch
                    } else if (listTopic.size >= DEFAULT_ITEM) {
                        addItemLoad(listTopic)
                    }
                    _topics.plusAssign(listTopic)
                }
                _resource.postValue(Resource.success(data = topics))
                currentPosition++
                _isLoading.value = false
            } catch (e: Exception) {
                _resource.postValue(Resource.error(data = null, message = e.message.toString()))
            }
        }
    }

    override fun onLoadData() {
        _isLoading.value = true
        Handler(Looper.getMainLooper()).postDelayed({
            _isLoading.value = false
            removeItemLoad()
            fetchTopics()
        }, DELAY_TIME)
    }

    override fun onRefresh() {
        _isLoading.value = true
        Handler(Looper.getMainLooper()).postDelayed({
            _photos.value?.clear()
            _topics.value?.clear()
            _isLoading.value = false
            currentPosition = DEFAULT_PAGE
            fetchTopics()
            fetchRandomPhoto()
        }, DELAY_TIME)
    }

    private fun removeItemLoad() {
        _topics.apply { value?.size?.let { value?.removeAt(it - ITEM_LAST) } }
    }

    private fun addItemLoad(listTopic: MutableList<Topic>) {
        listTopic.add(Topic("", "", "", PhotoCollection("", 0, Image())))
    }

    companion object {
        const val DELAY_TIME = 2000L
        const val ITEM_LAST = 1
    }
}
