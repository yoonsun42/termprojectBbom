package com.example.android.termprojectbbom;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.AvoidXfermode;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class UploadActivity extends AppCompatActivity {

    Button btnUpload;
    Button btnAdd;

    Button btnOK;
    Button btnCancel;

    ImageView image;

    ImageView imageDrawingPane;

    Bitmap bitmapMaster;
    Canvas canvasMaster;
    Bitmap bitmapDrawingPane;
    Canvas canvasDrawingPane;


    projectPt startPt;

    int x_start, x_end, y_start, y_end;

    private ImageView iv_t;
    private ImageView iv_pants;
    private ImageView iv_shoes;
    private ImageView iv_cap;
    private ImageView iv_glass;


    private List<ItemInfo> mItemInfos;

    private String picturePath;

    private static int RESULT_LOAD_IMAGE = 1;

    int item_kind;

    private boolean touch=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        initResource();
        initEvents();

    }

    protected void initResource()
    {
        btnUpload = (Button)findViewById(R.id.btnUpload);
        image = (ImageView)findViewById(R.id.image);
        imageDrawingPane = (ImageView)findViewById(R.id.drawingpane);
        btnUpload.setVisibility(View.VISIBLE);
        image.setVisibility(View.INVISIBLE);
        btnOK = (Button)findViewById(R.id.btnOK);
        btnCancel = (Button)findViewById(R.id.btnCancel);
        btnAdd = (Button)findViewById(R.id.btnAdd);

        mItemInfos = new ArrayList<ItemInfo>();

        iv_t = (ImageView)findViewById(R.id.image_t);
        iv_pants = (ImageView)findViewById(R.id.image_pants);
        iv_shoes = (ImageView)findViewById(R.id.image_shoes);
        iv_cap = (ImageView)findViewById(R.id.image_cap);
        iv_glass = (ImageView)findViewById(R.id.image_glass);

        iv_t.setImageResource(R.drawable.icon_blank);
        iv_pants.setImageResource(R.drawable.icon_blank);
        iv_shoes.setImageResource(R.drawable.icon_blank);
        iv_cap.setImageResource(R.drawable.icon_blank);
        iv_glass.setImageResource(R.drawable.icon_blank);
    }

    protected void initEvents()
    {
        btnUpload.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        selectImage();
                    }
                });

        btnOK.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Item item = new Item("test", picturePath);
                        item.setIndex(((MyApplication) getApplication()).getCount());
                        ((MyApplication) getApplication()).add(item);
                        if(mItemInfos.size()!=0)
                            item.addInfoList(mItemInfos);
                        setResult(RESULT_OK);
                        finish();
                    }
                });

        btnCancel.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setResult(RESULT_CANCELED);
                        finish();
                    }
                }
        );

        btnAdd.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view)
                    {
                        touch= true;
                        drawImage();
                        //showItemDialog();
                    }
                }
        );
    }

    private void showItemDialog(int x, int y, int x_end, int y_end)
    {

        LayoutInflater inflater=getLayoutInflater();
        final View dialogView= inflater.inflate(R.layout.dialog_item_add, null);

        final Button btn_t = (Button)dialogView.findViewById(R.id.btn_t);
        final Button btn_pants = (Button)dialogView.findViewById(R.id.btn_pants);
        final Button btn_shoes = (Button)dialogView.findViewById(R.id.btn_shoes);
        final Button btn_cap = (Button)dialogView.findViewById(R.id.btn_cap);
        final Button btn_glass = (Button)dialogView.findViewById(R.id.btn_glass);

        final Button btn_cancel_dialog = (Button)dialogView.findViewById(R.id.btnCancel);
        final Button btn_ok_dialog = (Button)dialogView.findViewById(R.id.btnOK);

        final EditText et_name = (EditText)dialogView.findViewById(R.id.et_name);
        final EditText et_price = (EditText)dialogView.findViewById(R.id.et_price);
        final EditText et_mall = (EditText)dialogView.findViewById(R.id.et_mall);
        final EditText et_info = (EditText)dialogView.findViewById(R.id.et_info);

        final int x_temp = x;
        final int y_temp = y;
        final int x_end_temp =x_end;
        final int y_end_temp =y_end;

        item_kind=0;

        btn_t.setOnClickListener(

                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        btn_t.setBackgroundResource(R.drawable.icon_t_black);
                        btn_pants.setBackgroundResource(R.drawable.icon_pants_white);
                        btn_shoes.setBackgroundResource(R.drawable.icon_shoes_white);
                        btn_cap.setBackgroundResource(R.drawable.icon_cap_white);
                        btn_glass.setBackgroundResource(R.drawable.icon_glass_white);
                        item_kind=1;
                    }
                });

        btn_pants.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        btn_t.setBackgroundResource(R.drawable.icon_t_white);
                        btn_pants.setBackgroundResource(R.drawable.icon_pants_black);
                        btn_shoes.setBackgroundResource(R.drawable.icon_shoes_white);
                        btn_cap.setBackgroundResource(R.drawable.icon_cap_white);
                        btn_glass.setBackgroundResource(R.drawable.icon_glass_white);
                        item_kind=2;
                    }
                });

        btn_shoes.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        btn_t.setBackgroundResource(R.drawable.icon_t_white);
                        btn_pants.setBackgroundResource(R.drawable.icon_pants_white);
                        btn_shoes.setBackgroundResource(R.drawable.icon_shoes_black);
                        btn_cap.setBackgroundResource(R.drawable.icon_cap_white);
                        btn_glass.setBackgroundResource(R.drawable.icon_glass_white);
                        item_kind=3;
                    }
                });

        btn_cap.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        btn_t.setBackgroundResource(R.drawable.icon_t_white);
                        btn_pants.setBackgroundResource(R.drawable.icon_pants_white);
                        btn_shoes.setBackgroundResource(R.drawable.icon_shoes_white);
                        btn_cap.setBackgroundResource(R.drawable.icon_cap_black);
                        btn_glass.setBackgroundResource(R.drawable.icon_glass_white);
                        item_kind = 4;
                    }
                });

        btn_glass.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        btn_t.setBackgroundResource(R.drawable.icon_t_white);
                        btn_pants.setBackgroundResource(R.drawable.icon_pants_white);
                        btn_shoes.setBackgroundResource(R.drawable.icon_shoes_white);
                        btn_cap.setBackgroundResource(R.drawable.icon_cap_white);
                        btn_glass.setBackgroundResource(R.drawable.icon_glass_black);
                        item_kind = 5;
                    }
                });



        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setView(dialogView);
        final AlertDialog dialog = alert.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = 1000;
        params.height = 1200;
        dialog.getWindow().setAttributes(params);

        btn_cancel_dialog.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });
        btn_ok_dialog.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //여기에는 아이템 추가하는 뭔가 더 추가
                        int kind = item_kind;
                        String name = et_name.getText().toString();
                        int price;
                        if((et_price.getText().toString())=="") price=0;
                        else price = Integer.parseInt("" + et_price.getText());
                        String mall = et_mall.getText().toString();
                        String info = et_info.getText().toString();
                        if(name=="")    name="정보없음";
                        if(mall=="")    mall="정보없음";
                        if(info=="")    info="정보없음";

                        ItemInfo itemInfo = new ItemInfo(kind, name, price, mall, info);
                        itemInfo.setRectangle(x_temp, y_temp, x_end_temp, y_end_temp);
                        mItemInfos.add(itemInfo);
                        dialog.cancel();
                        changeImageView(kind);

                    }
                });
        touch = false;
    }

    private void changeImageView(int kind)
    {
        switch(kind)
        {
            case 1:
                iv_t.setVisibility(View.VISIBLE);
                iv_t.setImageResource(R.drawable.icon_t_white);
                break;
            case 2:
                iv_pants.setVisibility(View.VISIBLE);
                iv_pants.setImageResource(R.drawable.icon_pants_white);
                break;
            case 3:
                iv_shoes.setVisibility(View.VISIBLE);
                iv_shoes.setImageResource(R.drawable.icon_shoes_white);
                break;
            case 4:
                iv_cap.setVisibility(View.VISIBLE);
                iv_cap.setImageResource(R.drawable.icon_cap_white);
                break;
            case 5:
                iv_glass.setVisibility(View.VISIBLE);
                iv_glass.setImageResource(R.drawable.icon_glass_white);
                break;
        }
    }

    private void drawImage()
    {
            image.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if(touch==false)
                        return true;

                    int action = event.getAction();

                    int x = (int) event.getX();
                    int y = (int) event.getY();

                    switch (action) {
                        case MotionEvent.ACTION_DOWN:
                            //textSource.setText("ACTION_DOWN- " + x + " : " + y);
                            startPt = projectXY((ImageView) v, bitmapMaster, x, y);
                            x_start = x;
                            y_start = y;
                            break;
                        case MotionEvent.ACTION_MOVE:
                            //textSource.setText("ACTION_MOVE- " + x + " : " + y);
                            drawOnRectProjectedBitMap((ImageView) v, bitmapMaster, x, y);
                            break;
                        case MotionEvent.ACTION_UP:
                            //textSource.setText("ACTION_UP- " + x + " : " + y);
                            drawOnRectProjectedBitMap((ImageView) v, bitmapMaster, x, y);
                            x_end = (int)event.getX();
                            y_end = (int)event.getY();
                            Log.d("ys", "x_end: "+x_end+ "y_end: "+y_end);
                            finalizeDrawing(x_start, y_start, x_end, y_end);
                            break;
                    }
    /*
     * Return 'true' to indicate that the event have been consumed.
     * If auto-generated 'false', your code can detect ACTION_DOWN only,
     * cannot detect ACTION_MOVE and ACTION_UP.
     */
                    return true;
                }
            });


    }

   private void selectImage()
   {
       Intent i = new Intent(
               Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

       startActivityForResult(i, RESULT_LOAD_IMAGE);

   }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap tempBitmap= null;

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();

            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            picturePath = cursor.getString(columnIndex);

            image.setVisibility(View.VISIBLE);
            btnUpload.setVisibility(View.INVISIBLE);

            try {
                tempBitmap = BitmapFactory.decodeStream(
                        getContentResolver().openInputStream(selectedImage));



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

                image.setImageBitmap(bitmapMaster);

                //Create bitmap of same size for drawing
                bitmapDrawingPane = Bitmap.createBitmap(
                        tempBitmap.getWidth(),
                        tempBitmap.getHeight(),
                        config);
                canvasDrawingPane = new Canvas(bitmapDrawingPane);
                imageDrawingPane.setImageBitmap(bitmapDrawingPane);


            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }



    }

    private projectPt projectXY(ImageView iv, Bitmap bm, int x, int y){
        if(x<0 || y<0 || x > iv.getWidth() || y > iv.getHeight()){
            //outside ImageView
            return null;
        }else{
            int projectedX = (int)((double)x * ((double)bm.getWidth()/(double)iv.getWidth()));
            int projectedY = (int)((double)y * ((double)bm.getHeight()/(double)iv.getHeight()));

            return new projectPt(projectedX, projectedY);
        }
    }

    private void drawOnRectProjectedBitMap(ImageView iv, Bitmap bm, int x, int y){
        if(x<0 || y<0 || x > iv.getWidth() || y > iv.getHeight()){
            //outside ImageView
            return;
        }else{
            int projectedX = (int)((double)x * ((double)bm.getWidth()/(double)iv.getWidth()));
            int projectedY = (int)((double)y * ((double)bm.getHeight()/(double)iv.getHeight()));

            //clear canvasDrawingPane
            canvasDrawingPane.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

            Paint paint = new Paint();
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.WHITE);
            paint.setStrokeWidth(3);
            canvasDrawingPane.drawRect(startPt.x, startPt.y, projectedX, projectedY, paint);
            imageDrawingPane.invalidate();


            //textSource.setText(x + ":" + y + "/" + iv.getWidth() + " : " + iv.getHeight() + "\n" +
            //                projectedX + " : " + projectedY + "/" + bm.getWidth() + " : " + bm.getHeight()
            //);
        }
    }

    private void finalizeDrawing(int x, int y, int x_end, int y_end){
        canvasMaster.drawBitmap(bitmapDrawingPane, 0, 0, null);

        showItemDialog(x, y, x_end, y_end);
    }

}

class projectPt{
    int x;
    int y;

    projectPt(int tx, int ty){
        x = tx;
        y = ty;
    }
}




