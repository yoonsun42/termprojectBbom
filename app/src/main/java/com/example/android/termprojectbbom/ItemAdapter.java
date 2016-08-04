package com.example.android.termprojectbbom;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yoonsun on 2016. 6. 3..
 */
public class ItemAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private Context mContext;
    private List<Item> itemList = new ArrayList<>();

    // Constructor
    public ItemAdapter(Context c, List<Item> itemList) {
        this.mContext = c;
        this.itemList = itemList;
        this.mInflater = LayoutInflater.from(c);
    }

    public int getCount() {
        return itemList.size();
    }

    public Object getItem(int position) {
        return itemList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.grid_item, null); //??
            convertView.setTag(R.id.picture, convertView.findViewById(R.id.picture));
        }
        Item item = (Item)getItem(position);
        imageView = (ImageView)convertView.getTag(R.id.picture);

        if(item.getLogoImageId()<0)
            imageView.setImageBitmap(BitmapFactory.decodeFile(item.getFilePath()));
        else
            imageView.setImageResource(item.getLogoImageId());
        imageView.setPadding(3, 3, 3, 3);

        return convertView;
    }

    public void add(Item item)
    {
        this.itemList.add(item);
        this.notifyDataSetChanged();
    }
}
