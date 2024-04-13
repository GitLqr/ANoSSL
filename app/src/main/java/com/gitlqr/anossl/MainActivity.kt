package com.gitlqr.anossl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.zhy.http.okhttp.OkHttpUtils
import com.zhy.http.okhttp.callback.StringCallback
import okhttp3.Call
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    companion object {
        /**
         * 支持协议 www.baidu.com   www.fresco-cn.org   api.github.com  api.oick.cn
         * TLS1.3       No              No                  Yes             Yes
         * TLS1.2       Yes             Yes                 Yes             Yes
         * TLS1.1       Yes             Yes                 No              No
         * TLS1.0       Yes             Yes                 No              No
         * SSL3.0       Yes             No                  No              No
         * SSL2.0       No              No                  No              No
         */
        // private const val TEST_URL = "https://www.baidu.com/"
        private const val TEST_URL = "https://www.fresco-cn.org/" // 这是一个https证书已经过期的网址
        // private const val TEST_URL = "https://api.github.com/"
        // private const val TEST_URL = "https://api.oick.cn/"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btnHttps).setOnClickListener {
            OkHttpUtils.get()
                .url(TEST_URL)
                .build()
                .execute(object : StringCallback() {
                    override fun onResponse(response: String?, id: Int) {
                        Log.e("ANoSSL", response)
                        Toast.makeText(this@MainActivity, "请求成功", Toast.LENGTH_SHORT).show()
                    }

                    override fun onError(call: Call?, e: Exception?, id: Int) {
                        e?.printStackTrace()
                        Toast.makeText(this@MainActivity, "请求失败", Toast.LENGTH_SHORT).show()
                    }
                })
        }
    }
}