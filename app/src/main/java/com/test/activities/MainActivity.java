package com.test.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.test.R;
import com.test.api.ApiService;
import com.test.models.LogoutResp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ImageView bgapp, clover, img_logOut, img_sach, img_theloai, img_hoaDonn, img_topSach, img_TK;
    LinearLayout textsplash, texthome, menus;
    Animation frombottom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frombottom = AnimationUtils.loadAnimation(this, R.anim.frombottom);

        bgapp = (ImageView) findViewById(R.id.bgapp);
        clover = (ImageView) findViewById(R.id.clover);
        textsplash = (LinearLayout) findViewById(R.id.textsplash);
        texthome = (LinearLayout) findViewById(R.id.texthome);
        menus = (LinearLayout) findViewById(R.id.menus);
        img_logOut = (ImageView) findViewById(R.id.img_dangxuat);
        img_sach = (ImageView) findViewById(R.id.img_Sach);
        img_theloai = (ImageView) findViewById(R.id.img_theloai);
        img_hoaDonn = findViewById(R.id.img_hoaDon);
        img_topSach = findViewById(R.id.img_sachBC);
        img_TK = findViewById(R.id.img_thongKe);
        /* Sk click */
        img_logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        img_theloai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListTypeBookActivity.class);
                startActivity(intent);
            }
        });

        img_sach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListBookActivity.class);
                startActivity(intent);
            }
        });

        img_hoaDonn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListBillActivity.class);
                startActivity(intent);
            }
        });

        img_topSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TopBookActivity.class);
                startActivity(intent);
            }
        });

        img_TK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TotalMoneyActivity.class);
                startActivity(intent);
            }
        });


        /* Animation view */
        bgapp.animate().translationY(-1900).setDuration(800).setStartDelay(300);
        clover.animate().alpha(0).setDuration(800).setStartDelay(600);
        textsplash.animate().translationY(140).alpha(0).setDuration(800).setStartDelay(300);

        texthome.startAnimation(frombottom);
        menus.startAnimation(frombottom);
    }

    public void logout() {
        ApiService.apiService.logout()
                .enqueue(new Callback<LogoutResp>() {
                    @Override
                    public void onResponse(Call<LogoutResp> call, Response<LogoutResp> response) {
                        Toast.makeText(MainActivity.this, "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onFailure(Call<LogoutResp> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Đăng xuất thất bại", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}

