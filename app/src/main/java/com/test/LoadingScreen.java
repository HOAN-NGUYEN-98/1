package com.test;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class LoadingScreen extends AppCompatActivity {


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        /* full man hinh */
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        /* ẩn ActionBar */
//        getSupportActionBar().hide();
//        getActionBar().setHomeButtonEnabled(true);
//        setContentView(R.layout.activity_loading_screen);
//        /* chuyển */
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                startActivity(new
//                        Intent(getApplicationContext(), LoginScreen.class));
//                finish();
//            }
//        }, 1888);


        @Override
        protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);

            //start StartActivity
            Intent intent = new Intent(this, LoginScreen.class);
            startActivity(intent);
            finish();
        }




}







