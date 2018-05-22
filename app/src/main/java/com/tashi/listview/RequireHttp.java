package com.tashi.listview;

import android.os.Handler;

import com.tashi.listview.Data.JSONManager;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;


public class RequireHttp {

    String url1 = "http://gank.io/api/random/data/";

    /*
    * 第一次加载个10个数据
    * 刷新一次加载5个
    * */
    public static final String Android = "Android";
    public static final String  Bonus = "福利";
    public static final String  IOS = "ios";
    public static final String  VideoForRest = "休息视频";
    public static final String ExpandResource = "扩展资源";
    public static final String FrontEnd = "前端";
    public static final String All = "all";

    String url2="";
    public static void Text(final String Type, final int Number, final int page,CallBack callBack) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                String data = "";
                HttpURLConnection connection=null;
                try {
                    String url = "http://gank.io/api/data/";
                    url = url + Type + "/" + Number + "/" + page;
                    URL Url = new URL(url);
                    URLConnection urlConnection = Url.openConnection();
                    connection = (HttpURLConnection) urlConnection;
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(10*1000);
                    connection.setReadTimeout(10*1000);
                    connection.connect();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        data += (line);
                    }
                    Analyze(data,Number);
                    reader.close();
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
                if(connection!=null){
                    connection.disconnect();
                }

                Handler handler = new Handler();
                handler.post(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        }
        );
    }
    private static void Analyze(String JSON,int number) throws JSONException {
        JSONManager.getGankData(JSON,number);

    }
    public interface CallBack{
        void onSuccess();
        void onFiled();
    }
}
