package com.ksballetba.eyetonisher.base

import io.reactivex.disposables.Disposable

open class BaseRepository {

    internal var mDisposable: Disposable? = null

    fun destory(): Unit {
        if (mDisposable !=null && !mDisposable!!.isDisposed)
            mDisposable!!.dispose()
    }
}