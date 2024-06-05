package com.test.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.test.R;
import com.test.api.ApiService;
import com.test.models.Book;
import com.test.models.BookRespone;
import com.test.models.TypeBookRespone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddBookActivity extends AppCompatActivity {
   // EditText id;
    EditText nameB, priceB, quan, prod, crea, type;
    Button btnAddBook, btnCancel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_sach);
        //id = findViewById(R.id.edt_id_book);
        nameB = findViewById(R.id.edt_ten_sach);
        priceB = findViewById(R.id.edt_gia_bia);
        quan = findViewById(R.id.edt_so_luong);
        prod = findViewById(R.id.edt_nxb);
        crea = findViewById(R.id.edt_tac_gia);
        type = findViewById(R.id.edt_type);
        btnAddBook = findViewById(R.id.btn_add_book);
        btnCancel = findViewById(R.id.btn_huy_book);


        btnAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBook();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToListBook();
            }
        });
    }

    private void backToListBook() {
        Intent intent = new Intent(AddBookActivity.this, ListBookActivity.class);
        startActivity(intent);
        finish();

    }


    private void addBook() {
       // String idBook = id.getText().toString();
        String name = nameB.getText().toString();
        String idType = type.getText().toString();
        String price = priceB.getText().toString();
        String quantity = quan.getText().toString();
        String creator = crea.getText().toString();
        String producer = prod.getText().toString();


        BookRespone bookRespone = new BookRespone( name, idType, price, quantity, creator, producer);
        if (name.equals("") || idType.equals("") || price.equals("") || quantity.equals("") || creator.equals("") || producer.equals("")) {
            Toast.makeText(AddBookActivity.this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
        } else {
            ApiService.apiService.addBook(bookRespone).enqueue(new Callback<BookRespone>() {
                @Override
                public void onResponse(Call<BookRespone> call, Response<BookRespone> response) {
                    BookRespone res = response.body();
                    if (response.code() == 404) {
                        Toast.makeText(AddBookActivity.this, "Type book không tồn tại!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (response.code() == 400) {
                        Toast.makeText(AddBookActivity.this, "Book đã tồn tại!", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        assert res != null;
                        Toast.makeText(AddBookActivity.this, "Lưu thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AddBookActivity.this, ListBookActivity.class);
                        startActivity(intent);
                        finish();

                    }
                }

                @Override
                public void onFailure(Call<BookRespone> call, Throwable throwable) {
                    Toast.makeText(AddBookActivity.this, "Thêm thất bại !", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }


}