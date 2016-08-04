package com.example.android.termprojectbbom;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {

    Button btnMen;
    Button btnWomen;

    Button btnCancel;
    Button btnOK;

    EditText etName;
    EditText etAge;
    EditText etEmail;
    EditText etPasssword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        initResource();
        initEvent();

    }

    private void initResource()
    {
        btnMen = (Button)findViewById(R.id.btnMen);
        btnWomen = (Button)findViewById(R.id.btnWomen);
        btnCancel = (Button)findViewById(R.id.btnCancel);
        btnOK = (Button)findViewById(R.id.btnOK);

        etName = (EditText)findViewById(R.id.et_name);
        etAge = (EditText)findViewById(R.id.et_age);
        etEmail = (EditText)findViewById(R.id.et_email);
        etPasssword = (EditText)findViewById(R.id.et_password);
    }

    private void initEvent()
    {
        btnMen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnMen.setBackgroundResource(R.drawable.icon_men);
                btnWomen.setBackgroundResource(R.drawable.icon_women_black);
            }
        });

        btnWomen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnWomen.setBackgroundResource(R.drawable.icon_women);
                btnMen.setBackgroundResource(R.drawable.icon_men_black);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //이거끄기
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
            }
        });


        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //가입하면 일어나는 일들
                String name = etName.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String password = etPasssword.getText().toString().trim();


                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
            }
        });

    }

}
