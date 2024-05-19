package com.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.test.api.ApiService;
import com.test.models.TypeBookRespone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddTypeBookScreen extends AppCompatActivity {
    EditText edtIdType, edtNameType, edtLocation, edtDescribe;
    Button add, back, list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_loai);
        edtIdType = findViewById(R.id.edMa);
        edtNameType = findViewById(R.id.edTen);
        edtDescribe = findViewById(R.id.edMo);
        edtLocation = findViewById(R.id.edVi);

        add = findViewById(R.id.btnThem);
        back = findViewById(R.id.btnHuy);
        list = findViewById(R.id.btnDanh);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addType();
            }
        });

        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backList();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToHome();
            }
        });
    }

    private void backList() {
        Intent intent = new Intent(AddTypeBookScreen.this, ListTypeBookScreen.class);
        startActivity(intent);
        finish();
    }

    private void backToHome() {
        Intent intent = new Intent(AddTypeBookScreen.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void addType() {
        String idType = edtIdType.getText().toString();
        String nameType = edtNameType.getText().toString();
        String location = edtLocation.getText().toString();
        String describe = edtDescribe.getText().toString();
        TypeBookRespone typeBody = new TypeBookRespone(idType, nameType, location, describe);
        ApiService.apiService.postType(typeBody).enqueue(new Callback<TypeBookRespone>() {
            @Override
            public void onResponse(Call<TypeBookRespone> call, Response<TypeBookRespone> response) {
                TypeBookRespone res = response.body();
                if (response.code() == 400) {
                    Toast.makeText(AddTypeBookScreen.this, "Thêm thất bại, ID đã tồn tại !", Toast.LENGTH_SHORT).show();
                } else {
                    assert res != null;
                    Toast.makeText(AddTypeBookScreen.this, "Thêm thành công !", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddTypeBookScreen.this,ListTypeBookScreen.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<TypeBookRespone> call, Throwable throwable) {
                Toast.makeText(AddTypeBookScreen.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
