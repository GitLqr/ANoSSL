/*
 * Copyright 2023 GitLqr
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gitlqr.anossl;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * 忽略https证书验证
 *
 * @author LQR
 * @since 2023/1/5
 */
public class NoSSLSocketClient {

    /**
     * 获取 SSL 协议的 SSLSocketFactory
     * 注：存在个别设备系统不兼容的情况
     */
    public static SSLSocketFactory getSSLSocketFactory() {
        try {
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, getTrustManager(), new SecureRandom());
            return sslContext.getSocketFactory();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static SSLSocketFactory getTLSSocketFactory() {
        return getTLSSocketFactory(new String[]{"TLSv1.2"});
    }

    /**
     * 获取 TLS 协议的 SSLSocketFactory
     *
     * @param enabledProtocols 要启用的 tls 协议，例如：{"TLSv1.1", "TLSv1.2"}
     */
    public static SSLSocketFactory getTLSSocketFactory(String[] enabledProtocols) {
        try {
            return new TLSSocketFactory(enabledProtocols, getTrustManager());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取TrustManager
     */
    public static TrustManager[] getTrustManager() {
        TrustManager[] trustAllCerts = new TrustManager[]{getX509TrustManager()};
        return trustAllCerts;
    }

    /**
     * 获取 X509TrustManager
     */
    public static X509TrustManager getX509TrustManager() {
        X509TrustManager x509TrustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[]{};
            }
        };
        return x509TrustManager;
    }

    /**
     * 获取HostnameVerifier
     */
    public static HostnameVerifier getHostnameVerifier() {
        HostnameVerifier hostnameVerifier = new HostnameVerifier() {
            @Override
            public boolean verify(String s, SSLSession sslSession) {
                return true;
            }
        };
        return hostnameVerifier;
    }
}
