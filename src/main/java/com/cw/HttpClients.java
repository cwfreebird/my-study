package com.cw;



import org.apache.http.Consts;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 * Created by waying.he on 2016/7/19.
 */
public class HttpClients {

    private static HttpClient httpClient;

    public static HttpClient getDefault() {
        if (httpClient == null) {
            synchronized (HttpClients.class) {
                if (httpClient == null) {
                    httpClient = HttpClientBuilder.create()
                            .setDefaultRequestConfig(RequestConfig.custom()
                                    .setConnectionRequestTimeout(50000)
                                    .setSocketTimeout(50000)
                                    .build())
                            .setDefaultConnectionConfig(ConnectionConfig.custom()
                                    .setCharset(Consts.UTF_8)
                                    .build())
                            .build();
                }
            }
        }
        return httpClient;
    }
}
