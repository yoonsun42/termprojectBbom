
package com.example.android.termprojectbbom;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class ItemInfoAdapter {
    private LayoutInflater mInflater;
    private Context mContext;
    private List<ItemInfo> itemInfoList = new ArrayList<>();

    // Constructor
    public ItemInfoAdapter(Context c, List<ItemInfo> itemInfoList) {
        this.mContext = c;
        this.itemInfoList = itemInfoList;
        this.mInflater = LayoutInflater.from(c);
    }

    public int getCount() {
        return itemInfoList.size();
    }

    public Object getItem(int position) {
        return itemInfoList.get(position);
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
        ItemInfo itemInfo = (ItemInfo)getItem(position);
        imageView = (ImageView)convertView.getTag(R.id.picture);

        imageView.setPadding(3, 3, 3, 3);

        return convertView;
    }

    public void add(ItemInfo itemInfo)
    {
        this.itemInfoList.add(itemInfo);
        //this.notifyDataSetChanged();
    }
}
