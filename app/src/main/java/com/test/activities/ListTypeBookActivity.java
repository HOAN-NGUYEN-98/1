package com.test.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.test.R;
import com.test.adapters.TypeBookAdapter;
import com.test.api.ApiService;
import com.test.models.TypeBookRespone;
import com.test.my_interface.IClickTypeBookListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListTypeBookActivity extends AppCompatActivity {
    private ProgressBar loadingPB;
    int count = 0;
    ImageView imageView;
    List<TypeBookRespone> typeBookResponeList;
    RecyclerView rcvData;
    private TypeBookAdapter typeBookAdapter;
    private NestedScrollView nestedSV;
    TypeBookAdapter.TypeBookViewHolder typeBookViewHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_the_loai);
        typeBookResponeList = new ArrayList<>();
        rcvData = findViewById(R.id.rcvTheLoai);
        nestedSV = findViewById(R.id.idNestedSV);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvData.setLayoutManager(linearLayoutManager);
        getListType();

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rcvData.addItemDecoration(itemDecoration);//nhin dep hon
        typeBookAdapter = new TypeBookAdapter(typeBookResponeList, new IClickTypeBookListener() {
            @Override
            public void conClickItemTypeBook(TypeBookRespone typeBookRespone) {
                onClickGoToDetail(typeBookRespone);
            }
        });

        imageView = findViewById(R.id.add_type);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListTypeBookActivity.this, AddTypeBookActivity.class);
                startActivity(intent);
                finish();
            }
        });

        nestedSV.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                // on scroll change we are checking when users scroll as bottom.
                if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {
                    count++;
                    loadingPB.setVisibility(View.VISIBLE);
                    if (count < 20) {
                        getListType();
                    }
                }
            }
        });

    }

    private void getListType() {
        ApiService.apiService.getTypeBook().enqueue(new Callback<List<TypeBookRespone>>() {
            @Override
            public void onResponse(Call<List<TypeBookRespone>> call, Response<List<TypeBookRespone>> response) {
                List<TypeBookRespone> json = response.body();
                typeBookResponeList.addAll(json);
                rcvData.setAdapter(typeBookAdapter);
            }

            @Override
            public void onFailure(Call<List<TypeBookRespone>> call, Throwable throwable) {
                Toast.makeText(ListTypeBookActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void onClickGoToDetail(TypeBookRespone bill) {
        Intent intent = new Intent(this, TypeBookDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_type", bill);
        intent.putExtras(bundle);
        startActivity(intent);

    }
}
