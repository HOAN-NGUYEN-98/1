package com.test;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.test.api.ApiService;
import com.test.models.Bill;
import com.test.adapters.BillAdapter;
import com.test.my_interface.IClickItemBillListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListBillScreen extends AppCompatActivity {
    List<Bill> billList;
    RecyclerView rcvData;
    private BillAdapter billAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        billList = new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_bill);
        rcvData = findViewById(R.id.rcvdata);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvData.setLayoutManager(linearLayoutManager);
        getList();
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rcvData.addItemDecoration(itemDecoration);//nhin dep hon

        billAdapter = new BillAdapter(billList, new IClickItemBillListener() {
            @Override
            public void conClickItemBill(Bill bill) {
                onClickGoToDetail(bill);
            }
        });



    }

    private void getList() {

        ApiService.apiService.getBill().enqueue(new Callback<List<Bill>>() {
            @Override
            public void onResponse(Call<List<Bill>> call, Response<List<Bill>> response) {
                List<Bill> json = response.body();
                billList.addAll(json);
                rcvData.setAdapter(billAdapter);
            }

            @Override
            public void onFailure(Call<List<Bill>> call, Throwable throwable) {
                Toast.makeText(ListBillScreen.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    private void onClickGoToDetail(Bill bill) {
        Intent intent = new Intent(this, BillDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_bill", bill);
        intent.putExtras(bundle);
        startActivity(intent);

    }


}
