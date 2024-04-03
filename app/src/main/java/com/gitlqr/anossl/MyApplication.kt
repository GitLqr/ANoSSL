package com.gitlqr.anossl

import android.app.Application
import com.zhy.http.okhttp.OkHttpUtils
import com.zhy.http.okhttp.log.LoggerInterceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initOkhttpUtils()
    }

    private fun initOkhttpUtils() {
        // 设置可访问所有的https网站
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(LoggerInterceptor("OkHttpUtils"))
            .connectTimeout(10000L, TimeUnit.MILLISECONDS)
            .readTimeout(10000L, TimeUnit.MILLISECONDS)
            // SSL 认证
            // .sslSocketFactory(
            //     NoSSLSocketClient.getSSLSocketFactory(),
            //     NoSSLSocketClient.getX509TrustManager()
            // )
            // TLS 认证
            .sslSocketFactory(
                NoSSLSocketClient.getTLSSocketFactory(),
                NoSSLSocketClient.getX509TrustManager()
            )
            .hostnameVerifier(NoSSLSocketClient.getHostnameVerifier())
            .build()
        OkHttpUtils.initClient(okHttpClient)
    }
}