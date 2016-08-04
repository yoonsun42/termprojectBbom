package com.example.android.termprojectbbom;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ListActivity extends AppCompatActivity {
    private int itemId;
    private int index;
    private ImageView image;



    ImageView imageDrawingPane;

    Bitmap bitmapMaster;
    Canvas canvasMaster;
    Bitmap bitmapDrawingPane;
    Canvas canvasDrawingPane;

    LinearLayout layout;

    private int like;

    private Button btn_left;
    private Button btn_right;
    private Button btn_like;
    private TextView tv_like;

    Button btnCancel;

    int x, y, x_end, y_end;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        initResource();

        Intent intent = getIntent();
        //itemId = intent.getIntExtra("itemId", R.mipmap.ic_launcher);
        index = intent.getIntExtra("index", 0);

        Item this_item = ((MyApplication)getApplication()).getItem(index);

        if(this_item.getLike()!=0)
            btn_like.setBackgroundResource(R.drawable.btn_like_on);

        if(index==0)
            btn_left.setVisibility(View.INVISIBLE);
        if(index==(((MyApplication) getApplication()).getCount()-1))
            btn_right.setVisibility(View.INVISIBLE);

        image = (ImageView)findViewById(R.id.image);

        if(this_item.getLogoImageId()>0)
            image.setImageResource(this_item.getLogoImageId());
        else {
            image.setImageBitmap(BitmapFactory.decodeFile(this_item.getFilePath()));
            if(this_item.getInfoSize()!=0)
            {
                ItemInfo temp = this_item.getItemInfo(0);
                x = temp.getX();
                x_end = temp.getX_end();
                y= temp.getY();
                y_end = temp.getY_end();
                Log.d("ys", "x: "+x+"y: "+y+"x_end: "+x_end+"y_end: "+y_end );
            }
        }

        like = this_item.getLike();
        tv_like.setText(""+like);


        initEvent();

        Log.d("ys", itemId + "로 받음");

    }

    private void initResource()
    {
        btn_left = (Button)findViewById(R.id.btnLeft);
        btn_right = (Button)findViewById(R.id.btnReight);
        btn_like = (Button)findViewById(R.id.btnLike);
        tv_like = (TextView)findViewById(R.id.tvLike);

        image = (ImageView)findViewById(R.id.image);
        //imageDrawingPane = (ImageView)findViewById(R.id.drawingpane);

        //canvasDrawingPane = new Canvas(bitmapDrawingPane);

        layout = (LinearLayout)findViewById(R.id.linear_info);

        btnCancel = (Button)findViewById(R.id.btnCancel);

    }

    private void initEvent()
    {
        btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToAnotherListActivity(index - 1);
                finish();
                //startActivity(intentUpload);
            }
        });

        btn_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToAnotherListActivity(index + 1);
                finish();
                //startActivity(intentUpload);
            }
        });

        btn_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((MyApplication) getApplication()).getItem(index).getLike() == 0) {
                    ((MyApplication) getApplication()).getItem(index).setLike(1);
                    like += 1;
                    btn_like.setBackgroundResource(R.drawable.btn_like_on);
                } else {
                    ((MyApplication) getApplication()).getItem(index).setLike(-1);
                    like -= 1;
                    btn_like.setBackgroundResource(R.drawable.btn_like_off);
                }

                tv_like.setText("" + like);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        showInfo();



    }

    private void showInfo()
    {
        int size = ((MyApplication) getApplication()).getItem(index).getInfoSize();

        if(size>0)
        {
            for(int i=0; i<size;i++)
            {
                LinearLayout tv = new LinearLayout(this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, ViewGroup.LayoutParams.MATCH_PARENT);
                tv.setLayoutParams(params);
                if(i!=0)    layout.addView(tv);

                ImageButton btnInfo = new ImageButton(this);
                btnInfo.setId(i);

                final ItemInfo itemInfo = ((MyApplication)getApplication()).getItem(index).getItemInfo(i);
                Log.d("ys", itemInfo.getKind() + "요교");
                switch(itemInfo.getKind()) {
                    case 1:
                        btnInfo.setBackgroundResource(R.drawable.icon_t_white);
                        break;
                    case 2:
                        btnInfo.setBackgroundResource(R.drawable.icon_pants_white);
                        break;
                    case 3:
                        btnInfo.setBackgroundResource(R.drawable.icon_shoes_white);

                        break;
                    case 4:
                        btnInfo.setBackgroundResource(R.drawable.icon_cap_white);
                        break;
                    case 5:
                        btnInfo.setBackgroundResource(R.drawable.icon_glass_white);
                        break;
                }
                layout.addView(btnInfo, 150, 150);
                btnInfo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showDialog(itemInfo.getName(), itemInfo.getPrice(), itemInfo.getMall(), itemInfo.getInfo());
                    }
                });
            }
        }

    }

    private void showDialog(String name, int price, String mall, String info)
    {
        LayoutInflater inflater=getLayoutInflater();
        final View dialogView= inflater.inflate(R.layout.dialog_item_show, null);

        final TextView tvName = (TextView)dialogView.findViewById(R.id.tv_name);
        final TextView tvPrice = (TextView)dialogView.findViewById(R.id.tv_price);
        final TextView tvMall = (TextView)dialogView.findViewById(R.id.tv_mall);
        final TextView tvInfo = (TextView)dialogView.findViewById(R.id.tv_info);

        tvName.setText(name);
        tvPrice.setText("" + price);
        tvMall.setText(mall);
        tvInfo.setText(info);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setView(dialogView);
        final AlertDialog dialog = alert.create();


        dialog.show();

        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = 800;
        params.height = 500;
        dialog.getWindow().setAttributes(params);

    }

    private void goToAnotherListActivity(int index)
    {
        Intent data = new Intent(ListActivity.this, ListActivity.class);
        data.putExtra("itemId", 0);
        data.putExtra("index", index);
        startActivity(data);
    }


    private void drawOnRectProjectedBitMap(ImageView iv, Bitmap bm, int x, int y){

            Item this_item = ((MyApplication)getApplication()).getItem(index);
            Bitmap tempBitmap = BitmapFactory.decodeFile(this_item.getFilePath());

            Bitmap.Config config;
            if(tempBitmap.getConfig() != null){
                config = tempBitmap.getConfig();
            }else{
                config = Bitmap.Config.ARGB_8888;
            }

            //bitmapMaster is Mutable bitmap
            bitmapMaster = Bitmap.createBitmap(
                    tempBitmap.getWidth(),
                    tempBitmap.getHeight(),
                    config);

            canvasMaster = new Canvas(bitmapMaster);
            canvasMaster.drawBitmap(tempBitmap, 0, 0, null);




        bitmapDrawingPane = Bitmap.createBitmap(
                tempBitmap.getWidth(),
                tempBitmap.getHeight(),
                config);
        canvasDrawingPane = new Canvas(bitmapDrawingPane);
        imageDrawingPane.setImageBitmap(bitmapDrawingPane);

        canvasDrawingPane.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(3);
        canvasDrawingPane.drawRect(x, y, x_end, y_end, paint);
        imageDrawingPane.invalidate();

        canvasMaster.drawBitmap(bitmapDrawingPane, 0, 0, null);

        }
    }


