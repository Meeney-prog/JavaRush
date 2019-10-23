package com.javarush.task.task40.task4002;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/* 
Опять POST, а не GET
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        Solution solution = new Solution();
        solution.sendPost("https://requestbin.jumio.com/1jwebpj1", "name=zapp&mood=good&locale=&id=777");
    }

    public void sendPost(String url, String urlParameters) throws Exception {
        HttpClient client = getHttpClient();
        HttpPost request = new HttpPost(url);
        URLEncodedUtils.parse(urlParameters, Charset.forName("UTF-8"));
        request.addHeader("User-Agent", "Mozilla/5.0");

        List<NameValuePair> valuePairs = new ArrayList<>();
        String[] s = urlParameters.split("&");
        for (String g : s)
            valuePairs.add(new BasicNameValuePair(g.substring(0, g.indexOf("=")), g.substring(g.indexOf("=") + 1)));

        request.setEntity(new UrlEncodedFormEntity(valuePairs));
        HttpResponse response = client.execute(request);
        HttpEntity entity = response.getEntity();
        System.out.println("Response Code: " + response.getStatusLine().getStatusCode());
        if (entity != null) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuilder result = new StringBuilder();
            String responseLine;
            while ((responseLine = bufferedReader.readLine()) != null) {
                result.append(responseLine);
            }
            System.out.println("Response: " + result.toString());
        }
    }

        protected HttpClient getHttpClient () {
            return HttpClientBuilder.create().build();
        }
    }
