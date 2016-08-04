package com.example.android.termprojectbbom;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private Toolbar toolbar;
    private ListView mItemListView;
    private GridView mItemGridView;

    private int temp_val;

    private List<Item> mItems;
    private List<Item> tempItems;
    private List<Item> likeItems;
    private ItemAdapter mItemAdapter;
    private ItemAdapter mItemAdapter_temp;
    private ItemAdapter mItemAdapter_like;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private FloatingActionButton mFab;


    private Button btn_userinfo;

    private Button toggle_boy;
    private Button toggle_girl;
    private Button toggle_cap;
    private Button toggle_t;
    private Button toggle_pants;
    private Button toggle_glass;
    private Button toggle_shoes;
    private Button toggle_like;

    //gender 0: 모두, 1: boy, 2:girl
    private int gender=0;


    private int kind=0;

    private int like=0;

    private int count=0;

    DrawerLayout drawer;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;

    //전체=0
    private int Gender=0; //남자면 1, 여자면 ㅈ
    private int Kind=0; // 모자 4, 상의1, 하의2, 신발 3, 기타 5
    private int Linke=0; //좋아요순으로할거면 1



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_main);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        initResource();

        //Item test_item = new Item("test", R.mipmap.image1);
        //Item test_item2 = new Item("test", R.mipmap.image2);
        //Item test_item3 = new Item("test", R.mipmap.images3);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.setDrawerIndicatorEnabled(false);

        toolbar.setNavigationIcon(R.drawable.icon_drawer);

        navigationView = (NavigationView) findViewById(R.id.nav_view);

        initEvent();


    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void setupActionBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // Show the Up button in the action bar.
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRefresh() {
        //fetchChattingRooms();
        mSwipeRefreshLayout.setRefreshing(false);
    }


    private void initResource()
    {
        mItemGridView = (GridView)findViewById(R.id.list);
        mItems = new ArrayList<Item>();
        mItems = ((MyApplication)getApplication()).items;
        tempItems = new ArrayList<Item>();
        //요기는 테스트

        ItemInfo temp_t = new ItemInfo(1, "예쁜", 5000, "고터 지하상가", "잘 늘어난");
        ItemInfo temp_shoes = new ItemInfo(3, "나이키테", 130000, "현대백화점", "");
        ItemInfo temp_pants = new ItemInfo(2, "슬랙스", 30000, "울트라마켓", "속이잘비친다");
        ItemInfo temp_cap = new ItemInfo(4, "스냅백", 20000, "현대백화점", "귀엽");

        List<ItemInfo> test1 = new ArrayList<ItemInfo>();
        List<ItemInfo> test2 = new ArrayList<ItemInfo>();
        List<ItemInfo> test3 = new ArrayList<ItemInfo>();

        test1.add(temp_pants);
        test1.add(temp_t);
        test1.add(temp_shoes);


        test2.add(temp_pants);
        test2.add(temp_t);

        test3.add(temp_t);
        test3.add(temp_pants);
        test3.add(temp_cap);

        Item item1 = new Item("test", R.mipmap.pic1);
        Item item2 = new Item("test", R.mipmap.pic2);
        Item item3 = new Item("test", R.mipmap.pic3);
        Item item4 = new Item("test", R.mipmap.pic4);
        Item item5 = new Item("test", R.mipmap.pic5);
        Item item6 = new Item("test", R.mipmap.pic6);
        item6.setLike(20);
        item6.addInfoList(test1);
        Item item7 = new Item("test", R.mipmap.pic7);
        item7.addInfoList(test2);
        item7.setLike(24);
        Item item8 = new Item("test", R.mipmap.pic8);
        item8.addInfoList(test3);
        item8.setLike(12);
        Item item9 = new Item("test", R.mipmap.pic9);
        Item item10 = new Item("test", R.mipmap.pic10);
        Item item11 = new Item("test", R.mipmap.pic11);
        Item item12 = new Item("test", R.mipmap.pic12);
        Item item13 = new Item("test", R.mipmap.pic13);
        Item item14 = new Item("test", R.mipmap.pic14);
        Item item15 = new Item("test", R.mipmap.pic15);
        Item item16 = new Item("test", R.mipmap.pic16);
        Item item17 = new Item("test", R.mipmap.pic17);
        Item item18 = new Item("test", R.mipmap.pic18);
        Item item19 = new Item("test", R.mipmap.pic19);
        Item item20 = new Item("test", R.mipmap.pic20);

        addItem(item3);
        addItem(item4);
        addItem(item5);
        addItem(item6);
        addItem(item7);
        addItem(item8);
        addItem(item1);
        addItem(item2);
        addItem(item8);
        addItem(item10);
        addItem(item11);
        addItem(item12);
        addItem(item13);
        addItem(item14);
        addItem(item15);
        addItem(item16);
        addItem(item17);
        addItem(item18);
        addItem(item19);
        addItem(item20);
        addItem(item3);
        addItem(item4);
        addItem(item5);
        addItem(item6);
        addItem(item7);
        addItem(item8);
        addItem(item1);
        addItem(item2);
        addItem(item8);
        addItem(item10);
        addItem(item11);
        addItem(item12);
        addItem(item13);
        addItem(item14);
        addItem(item15);
        addItem(item16);
        addItem(item17);
        addItem(item18);
        addItem(item19);
        addItem(item20);




        mItemAdapter = new ItemAdapter(this, mItems);
        mItemGridView.setAdapter(mItemAdapter);
        mSwipeRefreshLayout =  (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        mFab = (FloatingActionButton)findViewById(R.id.fab);
        mFab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.fab_color)));

        btn_userinfo = (Button)findViewById(R.id.btn_userinfo);

        toggle_boy = (Button)findViewById(R.id.button_boy);
        toggle_girl  = (Button)findViewById(R.id.button_girl);
        toggle_shoes = (Button)findViewById(R.id.button_shoes);
        toggle_cap  = (Button)findViewById(R.id.button_cap);
        toggle_t  = (Button)findViewById(R.id.button_t);
        toggle_pants  = (Button)findViewById(R.id.button_pants);
        toggle_glass  = (Button)findViewById(R.id.button_glass);

        toggle_like  = (Button)findViewById(R.id.button_like);


    }

    private void initEvent()
    {

        drawer.setDrawerListener(toggle);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                drawer.openDrawer(Gravity.LEFT);
            }
        });
        toggle.syncState();

        //final Intent intentUpload = new Intent(this, UploadActivity.class);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this, UploadActivity.class), 1); //혹시이상하면고치기
                mItemAdapter.notifyDataSetChanged();
                //startActivity(intentUpload);
            }
        });

        mItemGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Item item = (Item) parent.getItemAtPosition(position);
                goToListActivity(item);
            }
        });

        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
                //fetch어쩌고저쩌고
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
        mSwipeRefreshLayout.setOnRefreshListener(this);

        btn_userinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, UserinfoActivity.class));
            }


        });

        toggle_boy.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                if(gender==0) {
                    gender = 1;
                    toggle_boy.setBackgroundResource(R.drawable.btn_check);
                }
                else if(gender==1) {
                    gender = 0;
                    toggle_boy.setBackgroundResource(R.drawable.btn_blank);
                    toggle_girl.setBackgroundResource(R.drawable.btn_blank);
                }
                else if(gender==2) {
                    gender = 1;
                    toggle_boy.setBackgroundResource(R.drawable.btn_check);
                    toggle_girl.setBackgroundResource(R.drawable.btn_blank);
                }
            }

        });

        toggle_girl.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(gender==0)
                {
                    toggle_girl.setBackgroundResource(R.drawable.btn_check);
                    gender=2;
                }
                else if(gender==1)
                {
                    toggle_girl.setBackgroundResource(R.drawable.btn_check);
                    toggle_boy.setBackgroundResource(R.drawable.btn_blank);
                    gender=2;
                }
                else
                {
                    toggle_girl.setBackgroundResource(R.drawable.btn_blank);
                    gender=0;
                }

            }

        });

        toggle_t.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(kind==1) {
                    toggle_t.setBackgroundResource(R.drawable.btn_blank);
                    kind=0;
                    listChange();
                }
                else
                {
                    toggle_t.setBackgroundResource(R.drawable.btn_check);
                    toggle_cap.setBackgroundResource(R.drawable.btn_blank);
                    toggle_glass.setBackgroundResource(R.drawable.btn_blank);
                    toggle_pants.setBackgroundResource(R.drawable.btn_blank);
                    toggle_shoes.setBackgroundResource(R.drawable.btn_blank);
                    kind=1;
                    listChange();
                }

            }

        });


        toggle_cap.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(kind==4) {
                    toggle_cap.setBackgroundResource(R.drawable.btn_blank);
                    kind=0;
                    listChange();
                }
                else
                {
                    toggle_cap.setBackgroundResource(R.drawable.btn_check);
                    toggle_t.setBackgroundResource(R.drawable.btn_blank);
                    toggle_glass.setBackgroundResource(R.drawable.btn_blank);
                    toggle_pants.setBackgroundResource(R.drawable.btn_blank);
                    toggle_shoes.setBackgroundResource(R.drawable.btn_blank);
                    kind=4;
                    listChange();
                }

            }

        });

        toggle_pants.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(kind==2) {
                    toggle_pants.setBackgroundResource(R.drawable.btn_blank);
                    kind=0;
                    listChange();
                }
                else
                {
                    toggle_pants.setBackgroundResource(R.drawable.btn_check);
                    toggle_cap.setBackgroundResource(R.drawable.btn_blank);
                    toggle_glass.setBackgroundResource(R.drawable.btn_blank);
                    toggle_t.setBackgroundResource(R.drawable.btn_blank);
                    toggle_shoes.setBackgroundResource(R.drawable.btn_blank);
                    kind=2;
                    listChange();
                }

            }

        });

        toggle_shoes.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                if (kind == 3) {
                    toggle_shoes.setBackgroundResource(R.drawable.btn_blank);
                    kind = 0;
                    listChange();
                } else {
                    toggle_shoes.setBackgroundResource(R.drawable.btn_check);

                    toggle_cap.setBackgroundResource(R.drawable.btn_blank);
                    toggle_glass.setBackgroundResource(R.drawable.btn_blank);
                    toggle_pants.setBackgroundResource(R.drawable.btn_blank);
                    toggle_t.setBackgroundResource(R.drawable.btn_blank);
                    kind = 3;
                    listChange();
                }
            }

        });

        toggle_glass.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(kind==5) {
                    toggle_glass.setBackgroundResource(R.drawable.btn_blank);
                    kind=0;
                    listChange();
                }
                else
                {
                    toggle_glass.setBackgroundResource(R.drawable.btn_check);

                    toggle_cap.setBackgroundResource(R.drawable.btn_blank);
                    toggle_t.setBackgroundResource(R.drawable.btn_blank);
                    toggle_pants.setBackgroundResource(R.drawable.btn_blank);
                    toggle_shoes.setBackgroundResource(R.drawable.btn_blank);
                    kind=5;
                    listChange();
                }

            }

        });

        toggle_like.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Log.d("ys", "뭐지"+like);
                if(like==0) {
                    toggle_like.setBackgroundResource(R.drawable.btn_check);
                    like=1;
                    likeChange();
                }
                else if(like==1)
                {
                    toggle_like.setBackgroundResource(R.drawable.btn_blank);
                    like=0;
                    if(kind==0)
                        mItemAdapter.notifyDataSetChanged();
                    else
                        mItemAdapter_temp.notifyDataSetChanged();
                }

            }

        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK&&requestCode==1)
        {
            Log.d("ys", "여기오");
            mItemAdapter.notifyDataSetChanged();
        }
    }

    private void goToListActivity(Item item)
    {
        Intent data = new Intent(MainActivity.this, ListActivity.class);
        data.putExtra("itemId", item.getLogoImageId());
        data.putExtra("index", item.getIndex());
        Log.d("ys", item.getIndex() + "인덱스 잘 표시됨?");
        startActivity(data);
    }

    private void addItem(Item item)
    {
        item.setIndex(mItems.size());
        //mItems.add(item);
        ((MyApplication)getApplication()).add(item);
    }

    private void listChange()
    {
         tempItems.clear();

        if(kind==0)
        {
            for(int i=0; i<mItems.size();i++)
            {
                tempItems.add(mItems.get(i));
            }
        }
        else {

            for (int i = 0; i < mItems.size(); i++) {
                for (int j = 0; j < mItems.get(i).getInfoSize(); j++) {
                    if (mItems.get(i).getItemInfo(j).getKind() == kind)
                        tempItems.add(mItems.get(i));
                }
            }
        }
        mItemAdapter_temp = new ItemAdapter(this, tempItems);
        mItemGridView.setAdapter(mItemAdapter_temp);
        mItemAdapter_temp.notifyDataSetChanged();

    }

    private void likeChange()
    {
        likeItems = new ArrayList<Item>();
        if(kind==0)
        {
            for(int i=100; i>=0; i--)
            {
                for(int j=0; j<mItems.size();j++)
                {
                    if(mItems.get(j).getLike()==i)
                        likeItems.add(mItems.get(j));

                }
            }
        }
        else
        {
            for(int i=100; i>=0; i--)
            {
                for(int j=0; j<mItems.size();j++)
                {
                    if(tempItems.get(j).getLike()==i)
                        likeItems.add(mItems.get(j));

                }
            }
        }

        mItemAdapter_like = new ItemAdapter(this, likeItems);
        mItemGridView.setAdapter(mItemAdapter_like);
        mItemAdapter_like.notifyDataSetChanged();

    }



}
