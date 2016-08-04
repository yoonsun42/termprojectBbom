package com.example.android.termprojectbbom;

import android.app.Application;
import android.net.Uri;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yoonsun on 2016. 6. 6..
 */
public class MyApplication extends Application {

    public List<Item> items;

    public Uri uri;



    @Override
    public void onCreate() {
        //전역 변수 초기화
        super.onCreate();
        items = new ArrayList<Item>();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public void update(List<Item> newList)
    {
        items.clear();
        items = newList;
    }

    public void add(Item item)
    {

        items.add(item);
    }

    public Item getItem(int index)
    {
        return items.get(index);
    }

    public int getCount()
    {
        return items.size();
    }

    public Uri getUri() {   return uri; }

    public void setUri(Uri uri) {   this.uri = uri; }

}
