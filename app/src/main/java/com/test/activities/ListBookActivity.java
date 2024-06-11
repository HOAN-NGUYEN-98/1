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
import com.test.adapters.BookAdapter;
import com.test.api.ApiService;
import com.test.models.Bill;
import com.test.models.Book;
import com.test.models.BookRespone;
import com.test.my_interface.IClickBookListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListBookActivity extends AppCompatActivity {
    RecyclerView rcvdata;
    EditText edtSearch;
    List<Book> bookList;
    private BookAdapter bookAdapter;

    Button btnAddBook, search;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        bookList = new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_sach);
        btnAddBook = findViewById(R.id.btnAddBook);
        search = findViewById(R.id.btnTim);
        edtSearch = findViewById(R.id.edSearchBook);
        rcvdata = findViewById(R.id.rcvBook);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvdata.setLayoutManager(linearLayoutManager);
        getListBook();
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rcvdata.addItemDecoration(itemDecoration);//nhin dep hon


        btnAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListBookActivity.this, AddBookActivity.class);
                startActivity(intent);
                finish();
            }
        });
        bookAdapter = new BookAdapter(bookList, new IClickBookListener() {
            @Override
            public void conClickItemBook(Book book) {
                onClickGoToDetail(book);
            }

        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchBook();
            }
        });

    }

    private void searchBook() {
        setContentView(R.layout.item_sach);
        TextView id = findViewById(R.id.tvBookID);
        TextView name = findViewById(R.id.tvBookName);
        TextView quan = findViewById(R.id.tvSo_Luong);
        TextView price = findViewById(R.id.tvBookPrice);
        String idBook =edtSearch.getText().toString();
        if (idBook.equals("")) {
            Toast.makeText(ListBookActivity.this, "Hãy nhập ID book!", Toast.LENGTH_SHORT).show();
        } else {
            Log.d("Hoan", "searchBook: "+String.valueOf(edtSearch.getText()));
            ApiService.apiService.detailBookByID(idBook).enqueue(new Callback<Book>() {
                @Override
                public void onResponse(Call<Book> call, Response<Book> response) {
                    if (response.code() != 200) {
                        Toast.makeText(ListBookActivity.this, "ID không tồn tại!", Toast.LENGTH_SHORT).show();
                    } else {
                        assert response.body() != null;
                        id.setText(String.valueOf(response.body().getIdBook()));
                        name.setText(String.valueOf(response.body().getName()));
                        price.setText(String.valueOf(response.body().getPrice()));
                        quan.setText(String.valueOf(response.body().getQuantity()));
                    }
                }

                @Override
                public void onFailure(Call<Book> call, Throwable throwable) {
                    Toast.makeText(ListBookActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                }
            });
        }
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
                Toast.makeText(ListBookActivity.this, "Error", Toast.LENGTH_SHORT).show();
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
