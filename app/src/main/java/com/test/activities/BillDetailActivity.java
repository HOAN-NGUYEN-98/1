package com.test.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.test.R;
import com.test.adapters.BillAdapter;
import com.test.adapters.BillDetailAdapter;
import com.test.api.ApiService;
import com.test.models.Bill;
import com.test.models.BillDetailRespone;
import com.test.models.Detail;
import com.test.my_interface.IClickItemBillListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BillDetailActivity extends AppCompatActivity {
    TextView tvId,tvTotal;
    List<Detail> billList = new ArrayList<>();
    RecyclerView rcvData;
    private BillDetailAdapter billAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_bill_activitty);
        tvId = findViewById(R.id.tv_id_bill);
        rcvData = findViewById(R.id.rcvDetailBill);
        tvTotal= findViewById(R.id.tv_total);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvData.setLayoutManager(linearLayoutManager);
        getListDetailBill();
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rcvData.addItemDecoration(itemDecoration);//nhin dep hon
        billAdapter = new BillDetailAdapter(this, billList);

    }

    //when click show list detailbill

    private void getListDetailBill() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        Bill bill = (Bill) bundle.get("object_bill");
        assert bill != null;
        String idBill = bill.getIdBill();
        ApiService.apiService.detailBillByIdBill(idBill).enqueue(new Callback<List<Detail>>() {
            @Override
            public void onResponse(Call<List<Detail>> call, Response<List<Detail>> response) {

                List<Detail> json = response.body();
                assert json != null;
                Log.d("Hoan", "onResponse: " + json.get(0).toString());
                int tt;
                int sum = 0;
                List<Integer> numbers = new ArrayList<>();
                for (int i = 0; i < json.size(); i++) {
                    int pr = json.get(i).getPrice();
                    int qu = json.get(i).getQuantitySell();

                    tt = pr * qu;
                    numbers.add(tt);
                }
                for (int number : numbers) {
                    sum += number;
                }
                tvTotal.setText(String.valueOf(sum));
                tvId.setText(String.valueOf(json.get(0).getIdBill()));
                billList.addAll(json);
                rcvData.setAdapter(billAdapter);
            }

            @Override
            public void onFailure(Call<List<Detail>> call, Throwable throwable) {
                Toast.makeText(BillDetailActivity.this, "Error!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}