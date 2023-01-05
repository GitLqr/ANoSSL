# ANoSSL

该库针对 Okhttp 忽略 https 证书校验配置，通过以下步骤集成即可。该库特点：

- 忽略 https 证书（私有证书 或 第三方https证书已过期的接口请求）
- 兼容低端 Android 设备

### 1、依赖

```groovy
implementation "com.github.GitLqr:ANoSSL:v1.0.0"
```

> 最新版本：![Release Version](https://img.shields.io/github/v/release/GitLqr/ANoSSL.svg)

### 2、混淆规则

```proguard
-keep class com.gitlqr.anossl.** { *;}
```

### 3、使用

使用 `NoSSLSocketClient` 获取 `sslSocketFactory` 以及 `hostnameVerifier`：

```kotlin
OkHttpClient.Builder().apply {
    // SSL 证书
    sslSocketFactory(NoSSLSocketClient.getTLSSocketFactory())
    hostnameVerifier(NoSSLSocketClient.getHostnameVerifier())
    // 其他配置...
}.build()
```
