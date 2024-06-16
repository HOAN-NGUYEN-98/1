package com.test.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.test.R;
import com.test.api.ApiService;
import com.test.models.TypeBookRespone;
import com.test.models.TypeBookUpdateRespone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TypeBookDetailActivity extends AppCompatActivity {
    TextView tvId, tvName, tvMo, tvVi;
    Button btnUpdate, btnBack, btnDel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_the_loai_detail);
        btnBack = findViewById(R.id.btnCance);
        btnUpdate = findViewById(R.id.btnUpda);
        btnDel = findViewById(R.id.btnDelete);
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        TypeBookRespone bill = (TypeBookRespone) bundle.get("object_type");
        tvId = findViewById(R.id.edMatheloai);
        tvName = findViewById(R.id.edTentheloai);
        tvMo = findViewById(R.id.edMota);
        tvVi = findViewById(R.id.edVitri);
        assert bill != null;
        tvId.setText(String.valueOf(bill.getIdType()));
        tvName.setText(bill.getNameType());
        tvMo.setText(bill.getDescribe());
        tvVi.setText(bill.getLocation());

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteType();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateType();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToList();
            }
        });
    }

    private void deleteType() {
        String idType = tvId.getText().toString();
        ApiService.apiService.deleteType(idType).enqueue(new Callback<TypeBookRespone>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<TypeBookRespone> call, Response<TypeBookRespone> response) {
                TypeBookRespone res = response.body();
                if (response.code() == 400) {
                    Toast.makeText(TypeBookDetailActivity.this, "Hãy xóa book trước khi xóa type book !", Toast.LENGTH_SHORT).show();
                } else {
                    assert res != null;
                    Toast.makeText(TypeBookDetailActivity.this, "Delete success!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(TypeBookDetailActivity.this, ListTypeBookActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<TypeBookRespone> call, Throwable throwable) {
                Toast.makeText(TypeBookDetailActivity.this, "Xóa thất bại !", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void backToList() {
        Intent intent = new Intent(TypeBookDetailActivity.this, ListTypeBookActivity.class);
        startActivity(intent);
        finish();
    }

    private void updateType() {
        String idType = tvId.getText().toString();
        String nameType = tvName.getText().toString();
        String loType = tvVi.getText().toString();
        String desType = tvMo.getText().toString();

        TypeBookUpdateRespone typeBookRespone = new TypeBookUpdateRespone(idType, nameType, desType, loType);
        if (idType.equals("") || nameType.equals("") || desType.equals("") || loType.equals("") ) {
            Toast.makeText(TypeBookDetailActivity.this, "Vui lòng nhập đủ thông tin!", Toast.LENGTH_SHORT).show();
        }
        else {


        ApiService.apiService.updateType(typeBookRespone, idType).enqueue(new Callback<TypeBookUpdateRespone>() {
            @Override
            public void onResponse(Call<TypeBookUpdateRespone> call, Response<TypeBookUpdateRespone> response) {
                if(response.code()==200){
                    Toast.makeText(TypeBookDetailActivity.this, "Sửa thành công!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(TypeBookDetailActivity.this, ListTypeBookActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(TypeBookDetailActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TypeBookUpdateRespone> call, Throwable throwable) {
            }
        });
        }
    }
}
