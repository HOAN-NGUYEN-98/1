package com.test;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.test.api.ApiService;
import com.test.models.TypeBookRespone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TypeBookDetailActivity extends AppCompatActivity {
    TextView tvId, tvName, tvMo, tvVi;
    Button btnUpdate, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_the_loai_detail);
        btnBack = findViewById(R.id.btnCance);
        btnUpdate = findViewById(R.id.btnUpda);

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
        tvId.setText(bill.getIdType());
        tvName.setText(bill.getNameType());
        tvMo.setText(bill.getDescribe());
        tvVi.setText(bill.getLocation());

        //setContentView(R.layout.activity_list_the_loai);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateType();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Intent intent = new Intent(TypeBookDetailActivity.this, ListTypeBookScreen.class);
                startActivity(intent);
                finish();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToList();
            }
        });
    }

    private void backToList() {
        Intent intent = new Intent(TypeBookDetailActivity.this, ListTypeBookScreen.class);
        startActivity(intent);
        finish();
    }

    private void updateType() {
        String idType = tvId.getText().toString();
        String nameType = tvName.getText().toString();
        String loType = tvVi.getText().toString();
        String desType = tvMo.getText().toString();

        TypeBookRespone typeBookRespone = new TypeBookRespone(idType, nameType, desType, loType);
        ApiService.apiService.updateType(typeBookRespone, idType).enqueue(new Callback<TypeBookRespone>() {
            @Override
            public void onResponse(Call<TypeBookRespone> call, Response<TypeBookRespone> response) {
            }

            @Override
            public void onFailure(Call<TypeBookRespone> call, Throwable throwable) {
            }
        });
    }
}
