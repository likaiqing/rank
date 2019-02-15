package com.pandatv.tools;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author: likaiqing
 * @create: 2019-01-28 14:39
 **/
@Component
public class HttpTools {

    private HttpClient client = HttpClientBuilder.create().build();

    public String httpGet(String url) throws IOException {
        HttpGet request = new HttpGet(url);

        //添加请求头
        request.addHeader("User-Agent", "Mozilla/5.0");

        HttpResponse response = client.execute(request);

        return EntityUtils.toString(response.getEntity(), "utf-8");
    }
}
