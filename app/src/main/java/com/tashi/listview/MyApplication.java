package com.tashi.listview;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import com.tashi.listview.Data.Gank;

public class MyApplication extends Application {
    public static Gank gank ;
    @SuppressLint("StaticFieldLeak")
    public static Context context=null;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
    public static Context getThisContext(){
        return context;
    }
    public static void setGank(Gank gank){
        MyApplication.gank = gank;
    }
    public static Gank getGank() {
        return gank;
    }
}
