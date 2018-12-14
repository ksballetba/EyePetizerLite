package com.ksballetba.eyetonisher.data.source.remote

import com.ksballetba.eyetonisher.services.DownloadService
import okhttp3.MediaType
import okhttp3.ResponseBody
import okio.*

class ProgressResponseBody(val responseBody: ResponseBody, val downloadListener: DownloadService.DownloadListener) : ResponseBody() {

    private var bufferedSource: BufferedSource? = null

    override fun contentLength(): Long {
        return responseBody.contentLength()
    }

    override fun contentType(): MediaType? {
        return responseBody.contentType()
    }

    override fun source(): BufferedSource {
        if (bufferedSource == null) {
            bufferedSource = Okio.buffer(source(responseBody.source()))
        }
        return bufferedSource!!
    }

    private fun source(source: Source): Source {
        return object : ForwardingSource(source) {
            var totalBytesRead: Long = 0
            override fun read(sink: Buffer, byteCount: Long): Long {
                val bytesRead = super.read(sink, byteCount)
                totalBytesRead += if (bytesRead != -1.toLong()) bytesRead else 0
                if (bytesRead != -1.toLong()) {
                    downloadListener.onProgress(responseBody.hashCode(),(totalBytesRead * 100 / responseBody.contentLength()).toInt())
                }
                return bytesRead
            }
        }
    }

}