package com.example.android.termprojectbbom;

import java.util.List;

/**
 * Created by yoonsun on 2016. 6. 3..
 */
public class Item {
    private int index;
    private String name;
    private int logoImageId;
    private int like;
    private String filePath;

    private List<ItemInfo> itemInfoList;

    public Item(String name, int logoImageId)
    {
        this.name = name;
        this.logoImageId = logoImageId;
        this.like=0;
    }

    public Item(String name, String filePath)
    {
        this.name = name;
        this.filePath = filePath;
        this.logoImageId = -1;
    }

    public Item(String name, String filePath, List<ItemInfo> itemInfos)
    {
        this.name = name;
        this.filePath = filePath;
        this.logoImageId = -1;
        this.itemInfoList = itemInfos;
    }

    public int getLogoImageId() {   return this.logoImageId; }

    public int getIndex(){ return this.index; }

    public void setIndex(int index) {   this.index= index;  }

    public int getLike(){   return this.like; }

    public int setLike(int i){   return this.like+=i; }

    public String getFilePath(){   return this.filePath;   }

    public void addInfoList(List<ItemInfo> infos) { this.itemInfoList = infos;  }

    public ItemInfo getItemInfo(int index)  {   return itemInfoList.get(index); }

    public int getInfoSize()    {
        if(itemInfoList==null)
            return 0;
        else
            return itemInfoList.size();
    }

}
