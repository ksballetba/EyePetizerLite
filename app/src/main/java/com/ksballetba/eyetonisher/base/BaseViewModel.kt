package com.ksballetba.eyetonisher.base

import androidx.lifecycle.ViewModel

open class BaseViewModel(var repository: BaseRepository) : ViewModel() {

    override fun onCleared() {
        super.onCleared()
        this.repository.destory()
    }
}