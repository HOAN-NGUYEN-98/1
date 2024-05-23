package com.test.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.test.R;
import com.test.api.ApiService;
import com.test.models.Bill;
import com.test.models.BillDetailRespone;
import com.test.models.BookRespone;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddDetailBillActivity extends AppCompatActivity {
    EditText edtIdDetailBill, edtIdBook, edtQuantity;
    TextView tvidBill, tvDonGia;
    TextView tvIdBookResult, tvPriceResult, tvQuanResult, tvThanhTienResult;
    Button btnBuy, btnOrder, loadPrice, btnBackToListBill;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don_detail);
        setTitle("THANH TOÁN");
        edtIdDetailBill = findViewById(R.id.tv_ma_hoa_don_chi_tiet);
        edtIdBook = findViewById(R.id.edMaSach);
        edtQuantity = findViewById(R.id.edSoLuongMua);
        tvidBill = findViewById(R.id.edMaHoaDon);
        btnBuy = findViewById(R.id.btn_buy);
        tvDonGia = findViewById(R.id.tv_don_gia);
        loadPrice = findViewById(R.id.btn_load_price);
        tvThanhTienResult = findViewById(R.id.tv_thanh_tien_res);
        btnBackToListBill = findViewById(R.id.btn_back);
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        Bill bill = (Bill) bundle.get("add_bill");
        assert bill != null;
        String s = bill.getIdBill();
        tvidBill.setText(s);


        loadPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPriceBook();
            }
        });
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDetail();
            }
        });

        btnBackToListBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToListBill();
            }
        });
    }

    private void backToListBill() {
        Intent intent = new Intent(AddDetailBillActivity.this, ListBillActivity.class);
        startActivity(intent);
        finish();
    }


    private void getPriceBook() {
        String id_book = edtIdBook.getText().toString();
        ApiService.apiService.detailBookByID(id_book).enqueue(new Callback<BookRespone>() {
            @Override
            public void onResponse(Call<BookRespone> call, Response<BookRespone> response) {
                BookRespone res = response.body();
                if (response.code() == 404) {
                    Toast.makeText(AddDetailBillActivity.this, "Book không tồn tại!", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    assert res != null;
                    Log.d("BOOKKKKKKKKKKKKKKKKKKKKKKKKKKKK", res.getPrice());
                    tvDonGia.setText(res.getPrice());
                }
            }

            @Override
            public void onFailure(Call<BookRespone> call, Throwable throwable) {
                Toast.makeText(AddDetailBillActivity.this, "Error!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void addDetail() {
        String idDetailBill = edtIdDetailBill.getText().toString();
        String idBook = edtIdBook.getText().toString();
        String idBill = tvidBill.getText().toString();
        String quantitySell = edtQuantity.getText().toString();
        String price = tvDonGia.getText().toString();
        BillDetailRespone billDetailRespone = new BillDetailRespone(idDetailBill, idBook, idBill, quantitySell, price);
        if (idDetailBill.equals("") || idBook.equals("") || quantitySell.equals("")) {
            Toast.makeText(AddDetailBillActivity.this, "Vui lòng nhập đủ thông tin!", Toast.LENGTH_SHORT).show();

        } else {
            ApiService.apiService.postDetailBill(billDetailRespone).enqueue(new Callback<BillDetailRespone>() {
                @Override
                public void onResponse(Call<BillDetailRespone> call, Response<BillDetailRespone> response) {
                    BillDetailRespone res = response.body();

                    if (response.code() == 400) {
                        Toast.makeText(AddDetailBillActivity.this, "Đã tồn tại ID detail bill!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (response.code() == 404) {
                        Toast.makeText(AddDetailBillActivity.this, "Book chưa tồn tại, hãy tạo!", Toast.LENGTH_SHORT).show();
                        return;
                    } else {

                        assert response.body() != null;
                        Toast.makeText(AddDetailBillActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                        Double sl = Double.parseDouble(res.getQuantitySell());
                        Double dg = Double.parseDouble(res.getPrice());
                        Double tt = sl * dg;
                        tvThanhTienResult.setText(String.valueOf(tt));
                    }
                }

                @Override
                public void onFailure(Call<BillDetailRespone> call, Throwable throwable) {
                    Toast.makeText(AddDetailBillActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


}
