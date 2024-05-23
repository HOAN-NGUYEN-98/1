package com.test.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.test.R;
import com.test.adapters.BillAdapter;
import com.test.api.ApiService;
import com.test.models.Bill;
import com.test.my_interface.IClickItemBillListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListBillActivity extends AppCompatActivity {
    List<Bill> billList;
    EditText edT;
    RecyclerView rcvData;
    private BillAdapter billAdapter;
    Button btnAdd, btnFind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        billList = new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_bill);
        btnAdd = findViewById(R.id.btn_them_bill);
        btnFind = findViewById(R.id.btn_Find_Bill);
        rcvData = findViewById(R.id.rcvdata);
        edT = findViewById(R.id.edSearch);
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

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListBillActivity.this, AddBillActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findBill();
            }
        });

    }

    private void findBill() {
        setContentView(R.layout.item_bill);
        TextView id = findViewById(R.id.tv_idBill);
        TextView date = findViewById(R.id.tv_dateOfBuy);
        if (edT.getText().toString().equals("")) {
            Toast.makeText(ListBillActivity.this, "Hãy nhập ID bill!", Toast.LENGTH_SHORT).show();
        } else {
            ApiService.apiService.getDetailBill(edT.getText().toString()).enqueue(new Callback<Bill>() {
                @Override
                public void onResponse(Call<Bill> call, Response<Bill> response) {
                    if (response.code() != 200) {
                        Toast.makeText(ListBillActivity.this, "ID không tồn tại!", Toast.LENGTH_SHORT).show();
                    } else {
                        id.setText(response.body().getIdBill());
                        date.setText(response.body().getDateOfBuy());
                    }
                }

                @Override
                public void onFailure(Call<Bill> call, Throwable throwable) {
                    Toast.makeText(ListBillActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                }
            });
        }
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
                Toast.makeText(ListBillActivity.this, "Error", Toast.LENGTH_SHORT).show();
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