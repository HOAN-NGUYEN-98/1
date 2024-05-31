package com.test.activities;

import android.os.Bundle;
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
import com.test.my_interface.IClickItemBillListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BillDetailActivity extends AppCompatActivity {
    TextView tvId, tvDate;
    ArrayList<BillDetailRespone> billList;
    RecyclerView rcvData;
    private BillDetailAdapter billAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        billList = new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_bill_activitty);
        rcvData=findViewById(R.id.rcvDetailBill);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvData.setLayoutManager(linearLayoutManager);
        getListDetailBill();
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rcvData.addItemDecoration(itemDecoration);//nhin dep hon
        billAdapter = new BillDetailAdapter(this,billList);

    }

    //when click show list detailbill

    private void getListDetailBill() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        Bill bill = (Bill) bundle.get("object_bill");
        assert bill != null;
        String idBill=bill.getIdBill();
        ApiService.apiService.detailBillByIdBill(idBill).enqueue(new Callback<List<BillDetailRespone>>() {
            @Override
            public void onResponse(Call<List<BillDetailRespone>> call, Response<List<BillDetailRespone>> response) {
                List<BillDetailRespone> json = response.body();
                billList.addAll(json);
                rcvData.setAdapter(billAdapter);
            }

            @Override
            public void onFailure(Call<List<BillDetailRespone>> call, Throwable throwable) {
                Toast.makeText(BillDetailActivity.this, "Error!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}