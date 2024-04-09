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

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

/**
 * TLS 协议的 SSLSocketFactory
 * <p>
 * 在个别Android设备上在使用 SSL 协议时会出现系统低层报错的问题，这个是系统bug，强制使用 TLSv1.2 协议解决
 * https://www.freshbytelabs.com/2018/09/how-to-solve-sslhandshakeexception-in.html
 * https://stackoverflow.com/questions/29916962/javax-net-ssl-sslhandshakeexception-javax-net-ssl-sslprotocolexception-ssl-han
 *
 * @author LQR
 * @since 2023/1/5
 */
public class TLSSocketFactory extends SSLSocketFactory {

    private final String[] enabledProtocols = {"TLSv1.2"}; // {"TLSv1.1", "TLSv1.2"}
    private final SSLSocketFactory delegate;

    public TLSSocketFactory(TrustManager[] tm) throws KeyManagementException, NoSuchAlgorithmException {
        SSLContext context = SSLContext.getInstance("TLS");
        context.init(null, tm, new SecureRandom());
        delegate = context.getSocketFactory();
    }

    @Override
    public String[] getDefaultCipherSuites() {
        return delegate.getDefaultCipherSuites();
    }

    @Override
    public String[] getSupportedCipherSuites() {
        return delegate.getSupportedCipherSuites();
    }

    @Override
    public Socket createSocket() throws IOException {
        return enableTLSOnSocket(delegate.createSocket());
    }

    @Override
    public Socket createSocket(Socket s, String host, int port, boolean autoClose) throws IOException {
        return enableTLSOnSocket(delegate.createSocket(s, host, port, autoClose));
    }

    @Override
    public Socket createSocket(String host, int port) throws IOException, UnknownHostException {
        return enableTLSOnSocket(delegate.createSocket(host, port));
    }

    @Override
    public Socket createSocket(String host, int port, InetAddress localHost, int localPort) throws IOException, UnknownHostException {
        return enableTLSOnSocket(delegate.createSocket(host, port, localHost, localPort));
    }

    @Override
    public Socket createSocket(InetAddress host, int port) throws IOException {
        return enableTLSOnSocket(delegate.createSocket(host, port));
    }

    @Override
    public Socket createSocket(InetAddress address, int port, InetAddress localAddress, int localPort) throws IOException {
        return enableTLSOnSocket(delegate.createSocket(address, port, localAddress, localPort));
    }

    private Socket enableTLSOnSocket(Socket socket) {
        if ((socket instanceof SSLSocket)) {
            // 20240405：
            // Android 4.4 及以下版本可能存在一些奇葩问题，需要自己实现了一个 DelegateSSLSocket 来解决，
            // 但是 Android 5.0 及以上不要使用，OkHttp 在高版本中会调用一些 DelegateSSLSocket 没有复写的方法，导致 app 崩溃。
            if (isGeAndroid5()) {
                ((SSLSocket) socket).setEnabledProtocols(enabledProtocols);
                return socket;
            }
            /**
             * FIX: 修复个别设备网络请求时闪退问题
             * java.lang.ClassCastException: int[] cannot be cast to java.lang.String[]
             *     at com.android.org.conscrypt.OpenSSLSocketImpl.getEnabledProtocols(OpenSSLSocketImpl.java:802)
             *     at okhttp3.ConnectionSpec.isCompatible(ConnectionSpec.java:207)
             */
            socket = new DelegateSSLSocket((SSLSocket) socket) {
                @Override
                public void setEnabledProtocols(String[] protocols) {
                    // super.setEnabledProtocols(protocols);
                    super.setEnabledProtocols(enabledProtocols);
                }
            };
        }
        return socket;
    }

    private boolean isGeAndroid5() {
        try {
            Class<?> classVersion = Class.forName("android.os.Build$VERSION");
            Field fieldSdkInt = classVersion.getDeclaredField("SDK_INT");
            fieldSdkInt.setAccessible(true);
            int sdkInt = (int) fieldSdkInt.get(null);
            return sdkInt >= 21;
        } catch (Exception e) {
            // e.printStackTrace();
            return false;
        }
    }
}
