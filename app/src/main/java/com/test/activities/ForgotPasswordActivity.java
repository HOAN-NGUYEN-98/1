package com.test.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.test.R;

public class ForgotPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_forgot_password);
        final Button bt_qmk = (Button) findViewById(R.id.bt_qmkc);
        final Button bt_backqmk = (Button) findViewById(R.id.bt_backqmk);
        bt_qmk.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        OpenCodeForgotPassword();
                    }
                });

        bt_backqmk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenLogin();
            }
        });


    }

    public void OpenLogin() {
        Intent intent = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    public void OpenCodeForgotPassword() {
        Toast.makeText(ForgotPasswordActivity.this, "Liên hệ admin để lấy lại mật khẩu !!", Toast.LENGTH_SHORT).show();
    }
}
