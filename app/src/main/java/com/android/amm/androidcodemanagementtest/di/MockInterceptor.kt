package com.android.amm.androidcodemanagementtest.di

import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody

class MockInterceptor : Interceptor {

    val redir_400 = 400
    val redir_401 = 401
    val redir_404 = 404
    val redir_409 = 409
    val redir_4xx = 412

    val serverErr_500 = 500
    val serverErr_503 = 503
    val serverErr_504 = 504
    val serverErr_5xx = 512

    override fun intercept(chain: Interceptor.Chain): Response {

        val responseString = "Error Message"

        return chain.proceed(chain.request())
            .newBuilder()
            .code(serverErr_500)
            .protocol(Protocol.HTTP_2)
            .message(responseString)
            .body(
                ResponseBody.create(
                    "application/json".toMediaTypeOrNull(),
                    responseString.toByteArray()
                )
            )
            .addHeader("content-type", "application/json")
            .build()
    }
}