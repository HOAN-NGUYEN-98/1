package com.test;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.test.adapters.BookAdapter;
import com.test.api.ApiService;
import com.test.models.Book;
import com.test.my_interface.IClickBookListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListBookScreen extends AppCompatActivity {
    RecyclerView rcvdata;

    List<Book> bookList;
    private BookAdapter bookAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        bookList = new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_sach);
        rcvdata = findViewById(R.id.rcvBook);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvdata.setLayoutManager(linearLayoutManager);
        getListBook();
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rcvdata.addItemDecoration(itemDecoration);//nhin dep hon

        bookAdapter = new BookAdapter(bookList, new IClickBookListener() {
            @Override
            public void conClickItemBook(Book book) {
                onClickGoToDetail(book);
            }

        });

    }

    void getListBook() {
        ApiService.apiService.getListBook().enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                List<Book> json = response.body();
                assert json != null;
                bookList.addAll(json);
                rcvdata.setAdapter(bookAdapter);
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable throwable) {
                Toast.makeText(ListBookScreen.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void onClickGoToDetail(Book book) {
        Intent intent = new Intent(this, BookActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_book", book);
        intent.putExtras(bundle);
        startActivity(intent);

    }

}
