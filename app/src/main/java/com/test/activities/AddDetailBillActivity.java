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
    TextView tvIdBill, tvCreateDate,tvTotal;
    RecyclerView rcvData;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don_detail);
        setTitle("THANH TOÁN");

        tvIdBill = findViewById(R.id.tv_Ma_Hoa_Don);
        tvCreateDate = findViewById(R.id.tv_create_date);
        tvTotal= findViewById(R.id.tv_thanh_tien_res);
        rcvData = findViewById(R.id.rcv_detail);



    }




}
