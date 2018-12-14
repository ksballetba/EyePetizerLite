package com.ksballetba.eyetonisher.data.source.remote

import com.ksballetba.eyetonisher.services.DownloadService
import okhttp3.Interceptor
import okhttp3.Response

class ProgressDownloadInterceptor(val downloadListener: DownloadService.DownloadListener):Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        return response.newBuilder().body(
                ProgressResponseBody(response.body()!!,downloadListener)
        ).build()
    }
}