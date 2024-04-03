package com.gitlqr.anossl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.channels.SocketChannel;

import javax.net.ssl.HandshakeCompletedListener;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

/**
 * 代理的 SSLSocket
 * 注：目前是空实现
 *
 * @author LQR
 * @since 2024/4/3
 */
class DelegateSSLSocket extends SSLSocket {

    protected final SSLSocket delegate;

    DelegateSSLSocket(SSLSocket delegate) {
        this.delegate = delegate;
    }

    @Override
    public String[] getSupportedCipherSuites() {
        return delegate.getSupportedCipherSuites();
    }

    @Override
    public String[] getEnabledCipherSuites() {
        return delegate.getEnabledCipherSuites();
    }

    @Override
    public void setEnabledCipherSuites(String[] suites) {
        delegate.setEnabledCipherSuites(suites);
    }

    @Override
    public String[] getSupportedProtocols() {
        return delegate.getSupportedProtocols();
    }

    @Override
    public String[] getEnabledProtocols() {
        return delegate.getEnabledProtocols();
    }

    @Override
    public void setEnabledProtocols(String[] protocols) {
        delegate.setEnabledProtocols(protocols);
    }

    @Override
    public SSLSession getSession() {
        return delegate.getSession();
    }

    @Override
    public void addHandshakeCompletedListener(
            HandshakeCompletedListener listener) {
        delegate.addHandshakeCompletedListener(listener);
    }

    @Override
    public void removeHandshakeCompletedListener(
            HandshakeCompletedListener listener) {
        delegate.removeHandshakeCompletedListener(listener);
    }

    @Override
    public void startHandshake() throws IOException {
        delegate.startHandshake();
    }

    @Override
    public void setUseClientMode(boolean mode) {
        delegate.setUseClientMode(mode);
    }

    @Override
    public boolean getUseClientMode() {
        return delegate.getUseClientMode();
    }

    @Override
    public void setNeedClientAuth(boolean need) {
        delegate.setNeedClientAuth(need);
    }

    @Override
    public void setWantClientAuth(boolean want) {
        delegate.setWantClientAuth(want);
    }

    @Override
    public boolean getNeedClientAuth() {
        return delegate.getNeedClientAuth();
    }

    @Override
    public boolean getWantClientAuth() {
        return delegate.getWantClientAuth();
    }

    @Override
    public void setEnableSessionCreation(boolean flag) {
        delegate.setEnableSessionCreation(flag);
    }

    @Override
    public boolean getEnableSessionCreation() {
        return delegate.getEnableSessionCreation();
    }

    @Override
    public void bind(SocketAddress localAddr) throws IOException {
        delegate.bind(localAddr);
    }

    @Override
    public synchronized void close() throws IOException {
        delegate.close();
    }

    @Override
    public void connect(SocketAddress remoteAddr) throws IOException {
        delegate.connect(remoteAddr);
    }

    @Override
    public void connect(SocketAddress remoteAddr, int timeout)
            throws IOException {
        delegate.connect(remoteAddr, timeout);
    }

    @Override
    public SocketChannel getChannel() {
        return delegate.getChannel();
    }

    @Override
    public InetAddress getInetAddress() {
        return delegate.getInetAddress();
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return delegate.getInputStream();
    }

    @Override
    public boolean getKeepAlive() throws SocketException {
        return delegate.getKeepAlive();
    }

    @Override
    public InetAddress getLocalAddress() {
        return delegate.getLocalAddress();
    }

    @Override
    public int getLocalPort() {
        return delegate.getLocalPort();
    }

    @Override
    public SocketAddress getLocalSocketAddress() {
        return delegate.getLocalSocketAddress();
    }

    @Override
    public boolean getOOBInline() throws SocketException {
        return delegate.getOOBInline();
    }

    @Override
    public OutputStream getOutputStream() throws IOException {
        return delegate.getOutputStream();
    }

    @Override
    public int getPort() {
        return delegate.getPort();
    }

    @Override
    public synchronized int getReceiveBufferSize() throws SocketException {
        return delegate.getReceiveBufferSize();
    }

    @Override
    public SocketAddress getRemoteSocketAddress() {
        return delegate.getRemoteSocketAddress();
    }

    @Override
    public boolean getReuseAddress() throws SocketException {
        return delegate.getReuseAddress();
    }

    @Override
    public synchronized int getSendBufferSize() throws SocketException {
        return delegate.getSendBufferSize();
    }

    @Override
    public int getSoLinger() throws SocketException {
        return delegate.getSoLinger();
    }

    @Override
    public synchronized int getSoTimeout() throws SocketException {
        return delegate.getSoTimeout();
    }

    @Override
    public boolean getTcpNoDelay() throws SocketException {
        return delegate.getTcpNoDelay();
    }

    @Override
    public int getTrafficClass() throws SocketException {
        return delegate.getTrafficClass();
    }

    @Override
    public boolean isBound() {
        return delegate.isBound();
    }

    @Override
    public boolean isClosed() {
        return delegate.isClosed();
    }

    @Override
    public boolean isConnected() {
        return delegate.isConnected();
    }

    @Override
    public boolean isInputShutdown() {
        return delegate.isInputShutdown();
    }

    @Override
    public boolean isOutputShutdown() {
        return delegate.isOutputShutdown();
    }

    @Override
    public void sendUrgentData(int value) throws IOException {
        delegate.sendUrgentData(value);
    }

    @Override
    public void setKeepAlive(boolean keepAlive) throws SocketException {
        delegate.setKeepAlive(keepAlive);
    }

    @Override
    public void setOOBInline(boolean oobinline) throws SocketException {
        delegate.setOOBInline(oobinline);
    }

    @Override
    public void setPerformancePreferences(int connectionTime, int latency,
                                          int bandwidth) {
        delegate.setPerformancePreferences(connectionTime, latency,
                bandwidth);
    }

    @Override
    public synchronized void setReceiveBufferSize(int size)
            throws SocketException {
        delegate.setReceiveBufferSize(size);
    }

    @Override
    public void setReuseAddress(boolean reuse) throws SocketException {
        delegate.setReuseAddress(reuse);
    }

    @Override
    public synchronized void setSendBufferSize(int size)
            throws SocketException {
        delegate.setSendBufferSize(size);
    }

    @Override
    public void setSoLinger(boolean on, int timeout) throws SocketException {
        delegate.setSoLinger(on, timeout);
    }

    @Override
    public synchronized void setSoTimeout(int timeout)
            throws SocketException {
        delegate.setSoTimeout(timeout);
    }

    @Override
    public void setTcpNoDelay(boolean on) throws SocketException {
        delegate.setTcpNoDelay(on);
    }

    @Override
    public void setTrafficClass(int value) throws SocketException {
        delegate.setTrafficClass(value);
    }

    @Override
    public void shutdownInput() throws IOException {
        delegate.shutdownInput();
    }

    @Override
    public void shutdownOutput() throws IOException {
        delegate.shutdownOutput();
    }

    @Override
    public String toString() {
        return delegate.toString();
    }

    @Override
    public boolean equals(Object o) {
        return delegate.equals(o);
    }
}