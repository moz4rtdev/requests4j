package com.github.moz4rtdev.requests4j;

import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;

public class ProxySet {

    private Authenticator proxyAuth;
    private Proxy proxy;
    private Proxy.Type proxyType;

    public void setProxy(String proxyTxt) {
        this.proxyType = (proxyTxt.contains("http") ||
                proxyTxt.contains("https"))
            ? Proxy.Type.HTTP
            : Proxy.Type.SOCKS;
        if (proxyTxt.contains("@")) {
            setProxyPrivate(proxyTxt);
        } else {
            setProxyPublic(proxyTxt);
        }
    }

    private void setProxyPublic(String proxyTxt) {
        String[] proxySlice = proxyTxt.split(":");
        Proxy proxy = new Proxy(
            proxyType,
            new InetSocketAddress(
                proxySlice[1].replace("//", ""),
                Integer.parseInt(proxySlice[2])
            )
        );
        this.proxy = proxy;
    }

    private void setProxyPrivate(String proxyTxt) {
        String[] proxySlice = proxyTxt.split("@");
        setProxyAuth(proxySlice[0]);
        String[] proxyIpPort = proxySlice[1].split(":");
        Proxy proxy = new Proxy(
            proxyType,
            new InetSocketAddress(
                proxyIpPort[0].replace("//", ""),
                Integer.parseInt(proxyIpPort[1])
            )
        );
        this.proxy = proxy;
    }

    private void setProxyAuth(String auth) {
        String[] authSlice = auth.split(":");
        Authenticator proxyAuth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                    authSlice[1].replace("//", ""),
                    authSlice[2].toCharArray()
                );
            }
        };
        this.proxyAuth = proxyAuth;
    }

    public Proxy getProxy() {
        return proxy;
    }

    public Authenticator getProxyAuth() {
        return proxyAuth;
    }
}
