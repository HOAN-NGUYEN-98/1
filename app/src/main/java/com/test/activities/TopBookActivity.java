package com.test.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.test.R;
import com.test.adapters.TopBookAdapter;
import com.test.api.ApiService;
import com.test.models.TopBook;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopBookActivity extends AppCompatActivity {
    TextView tvId, tvName, tvQuan;
    Button btnTim;
    RecyclerView rcvData;
    TopBookAdapter adapter;

    List<TopBook> top; ;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_ban_chay);
        rcvData = findViewById(R.id.lvBookTop);
//        btnTim=findViewById(R.id.viewsach);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvData.setLayoutManager(linearLayoutManager);
        getList();
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rcvData.addItemDecoration(itemDecoration);//nhin dep hon
        adapter = new TopBookAdapter(top);

//        btnTim.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //findTop();
//            }
//        });
    }


    private void getList() {
        top=new ArrayList<>();
        ApiService.apiService.topBook().enqueue(new Callback<List<TopBook>>() {
            @Override
            public void onResponse(Call<List<TopBook>> call, Response<List<TopBook>> response) {
                List<TopBook> topBooks=response.body();
                top.addAll(topBooks);
                rcvData.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<TopBook>> call, Throwable throwable) {
                Toast.makeText(TopBookActivity.this, "Error!", Toast.LENGTH_SHORT).show();
            }
        });
    }

//    private void findTop() {
//        ApiService.apiService.
//        setContentView(R.layout.item_top_book);
//        tvId =findViewById(R.id.tvBookIDTop);
//        tvName=findViewById(R.id.tvBookNameTop);
//        tvQuan=findViewById(R.id.tvSoLuongTop);
//    }


}
