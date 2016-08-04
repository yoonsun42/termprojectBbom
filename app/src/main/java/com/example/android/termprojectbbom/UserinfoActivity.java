package com.example.android.termprojectbbom;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.io.FileNotFoundException;

public class UserinfoActivity extends AppCompatActivity {

    Button btnCancel;

    ImageButton image;

    private String picturePath;

    private static int RESULT_LOAD_IMAGE = 1;

    private Uri selectedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_userinfo);

        btnCancel = (Button)findViewById(R.id.btnCancel);
        image = (ImageButton)findViewById(R.id.image);

        if(((MyApplication)getApplication()).getUri()!=null) {
            Log.d("ys", "uri: " + ((MyApplication)getApplication()).getUri().toString());
            image.setImageURI(((MyApplication) getApplication()).getUri());
        }


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        image.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        selectImage();
                        if(selectedImage!=null)
                            ((MyApplication)getApplication()).setUri(selectedImage);
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
            selectedImage = data.getData();

            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            picturePath = cursor.getString(columnIndex);

            image.setVisibility(View.VISIBLE);

            image.setImageURI(selectedImage);
            /*
            try {
                tempBitmap = BitmapFactory.decodeStream(
                        getContentResolver().openInputStream(selectedImage));

                Bitmap bitmapMaster;
                Canvas canvasMaster;

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


            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
*/
        }



    }

}
