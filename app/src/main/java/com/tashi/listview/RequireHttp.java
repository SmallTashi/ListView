package com.tashi.listview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class RequireHttp {

    String url1 = "http://gank.io/api/random/data/";

    private static final String Android = "Android";
    private static final String  Bonus = "福利";
    private static final String  IOS = "ios";
    private static final String  VideoForRest = "休息视频";
    private static final String ExpandResource = "扩展资源";
    private static final String FrontEnd = "前端";
    private static final String All = "all";

    String url2="";
    private static Runnable Text(final String Type, final int number, final int page) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String data = "";
                try {
                    HttpURLConnection connection;
                    String url = "http://gank.io/api/data/";
                    url = url + Type + "/" + number + "/" + page;
                    URL Url = new URL(url);
                    URLConnection urlConnection = Url.openConnection();
                    connection = (HttpURLConnection) urlConnection;
                    connection.setRequestMethod("GET");
                    connection.connect();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        data += (line);
                    }
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.run();
        );
    }
}
