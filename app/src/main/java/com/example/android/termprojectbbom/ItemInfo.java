package com.example.android.termprojectbbom;

/**
 * Created by yoonsun on 2016. 6. 6..
 */
public class ItemInfo {
    //종류 t는 1, pants 2, shoes 3, cap 4, glass 5
    private int kind;
    private String name;
    private int price;
    private String mall;
    private String info;

    private int x;
    private int y;
    private int x_end;
    private int y_end;

    ItemInfo()
    {
    }
    ItemInfo(int kind, String name, int price, String mall, String info)
    {
        this.kind = kind;
        this.name = name;
        this.price= price;
        this.mall = mall;
        this.info = info;
    }

    public void setRectangle(int x, int y, int x_end, int y_end)
    {
        this.x= x;
        this.y = y;
        this.x_end = x_end;
        this.y_end= y_end;
    }

    public String getName() {return name;}
    public int getPrice()   {return price;}
    public String getMall() {return mall;}
    public String getInfo() {return info;}

    public int getX()   {return x;}
    public int getY()   {return y;}
    public int getX_end() {return x_end;}
    public int getY_end() {return y_end;}

    public int getKind() {return kind;}
}
