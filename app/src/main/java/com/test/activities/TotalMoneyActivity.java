package com.test.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.test.R;
import com.test.api.ApiService;
import com.test.models.TotalMoney;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TotalMoneyActivity extends AppCompatActivity {
    TextView tvDate,tvMonth,tvYear;
    Button btnBack;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_thong_ke);
        tvDate=findViewById(R.id.tvThongKeNgay);
        tvMonth=findViewById(R.id.tvThongKeThang);
        tvYear=findViewById(R.id.tvThongKeNam);
        btnBack=findViewById(R.id.btn_backThongKe);

        ApiService.apiService.totalMoney().enqueue(new Callback<TotalMoney>() {
            @Override
            public void onResponse(Call<TotalMoney> call, Response<TotalMoney> response) {
                TotalMoney totalMoney = response.body();
                assert totalMoney != null;
                tvDate.setText(String.valueOf(totalMoney.getDateSum()));
                tvMonth.setText(String.valueOf(totalMoney.getMonthSum()));
                tvYear.setText(String.valueOf(totalMoney.getYearSum()));

            }

            @Override
            public void onFailure(Call<TotalMoney> call, Throwable throwable) {
                Toast.makeText(TotalMoneyActivity.this, "Error!", Toast.LENGTH_SHORT).show();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TotalMoneyActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }


}
