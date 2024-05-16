package com.test;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.LongDef;
import androidx.appcompat.app.AppCompatActivity;

import com.test.api.ApiService;
import com.test.models.Bill;
import com.test.models.BillDetailRespone;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.HTTP;

public class BillDetailActivity extends AppCompatActivity {

    TextView tvIdBill, tvDetailBill, tvIdBook, tvQuantity, tvPrice;
    Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.detail_bill_activitty);

        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        Bill bill = (Bill) bundle.get("object_bill");


        tvIdBill = findViewById(R.id.tv_id_bill);
        tvDetailBill = findViewById(R.id.tv_id_detail_bill);
        tvIdBook = findViewById(R.id.tv_id_book);
        tvQuantity = findViewById(R.id.tv_quantity);
        tvPrice = findViewById(R.id.tv_price);

        delete = findViewById(R.id.btn_delete_detail_bill);
        assert bill != null;
        String idBill = bill.getIdBill();
        ApiService.apiService.detailBill(idBill).enqueue(new Callback<BillDetailRespone>() {
            @Override
            public void onResponse(Call<BillDetailRespone> call, Response<BillDetailRespone> response) {
                BillDetailRespone billDetailRespone = response.body();
                assert billDetailRespone != null;
                tvDetailBill.setText(billDetailRespone.idDetailBill);
                tvIdBill.setText(billDetailRespone.idBill);
                tvIdBook.setText(billDetailRespone.idBook);
                tvQuantity.setText(billDetailRespone.quantitySell);
                tvPrice.setText(billDetailRespone.price);

            }

            @Override
            public void onFailure(Call<BillDetailRespone> call, Throwable throwable) {
                Toast.makeText(BillDetailActivity.this, "Not exist detail bill !", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(BillDetailActivity.this, ListBillScreen.class);
                startActivity(intent);
                finish();
            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteteDetailBill();
                tvDetailBill.setText("");
                tvIdBill.setText("");
                tvIdBook.setText("");
                tvQuantity.setText("");
                tvPrice.setText("");
                Toast.makeText(BillDetailActivity.this,"Delete success",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteteDetailBill() {
        String idBillDeatail = tvDetailBill.getText().toString();
        ApiService.apiService.deleteDetailBill(idBillDeatail).enqueue(new Callback<BillDetailRespone>() {

            @Override
            public void onResponse(Call<BillDetailRespone> call, Response<BillDetailRespone> response) {
                Log.d("ABCD", "ABCD: ");
            }
            @Override
            public void onFailure(Call<BillDetailRespone> call, Throwable throwable) {
            }
        });


    }

}