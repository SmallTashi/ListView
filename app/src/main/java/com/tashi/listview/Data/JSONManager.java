package com.tashi.listview.Data;

import com.tashi.listview.MyApplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;


public class JSONManager {
    public static void getGankData(String data,int number) throws JSONException {
        JSONObject object = new JSONObject(data);
        ArrayList<Gank.ResultsBean> resultsBeans = new ArrayList<>();

        Gank gank = new Gank();
        gank.setError(object.getBoolean("error"));
        if(!gank.isError()){
            JSONArray result = new JSONArray(object.getJSONArray(data));
            for (int i = 0; i < number; i++) {
                Gank.ResultsBean resultsBean = new Gank.ResultsBean();
                JSONObject bean = new JSONObject((Map) result.getJSONObject(i));
                resultsBean.set_id(bean.getString("_id"));
                resultsBean.setCreatedAt(bean.getString("createAt"));
                resultsBean.setDesc(bean.getString("desc"));
                resultsBean.setPublishedAt(bean.getString("publishedAt"));
                resultsBean.setSource(object.getString("source"));
                resultsBean.setType(bean.getString("type"));
                resultsBean.setUrl(bean.getString("url"));
                resultsBean.setWho(bean.getString("who"));
                resultsBean.setUsed(bean.getBoolean("used"));
                resultsBeans.add(resultsBean);
            }
        }
        gank.setResults(resultsBeans);
        MyApplication.setGank(gank);

    }



}
