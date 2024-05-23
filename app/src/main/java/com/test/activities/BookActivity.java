package com.test.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.test.R;
import com.test.api.ApiService;
import com.test.models.Book;
import com.test.models.BookRespone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookActivity extends AppCompatActivity {
    TextView tvId, tvName, tvCreator, tvProducer, tvPrice, tvQuantity, tvIdType;
    Button del, back, upd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sach_detail);
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        Book book = (Book) bundle.get("object_book");
        tvId = findViewById(R.id.edMaSachsua);
        tvName = findViewById(R.id.edTenSachsua);
        tvCreator = findViewById(R.id.edTacGiasua);
        tvProducer = findViewById(R.id.edNXBsua);
        tvPrice = findViewById(R.id.edGiaBiasua);
        tvQuantity = findViewById(R.id.edSoLuongsua);
        tvIdType = findViewById(R.id.edMa_the_loai);
        del = findViewById(R.id.btn_xoa_book);
        back = findViewById(R.id.btnCancesua);
        upd = findViewById(R.id.btnUpdasua);
        assert book != null;
        tvId.setText(book.getIdBook());
        tvName.setText(book.getName());
        tvCreator.setText(book.getCreator());
        tvProducer.setText(book.getProducer());
        tvPrice.setText(book.getPrice());
        tvQuantity.setText(book.getQuantity());
        tvIdType.setText(book.getIdType());

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteBook();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookActivity.this, ListBookActivity.class);
                startActivity(intent);
                finish();
            }
        });
        upd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateBook();
                try {
                    Thread.sleep(2000);
                    Intent intent = new Intent(BookActivity.this, ListBookActivity.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(BookActivity.this, "Sửa thành công!", Toast.LENGTH_SHORT).show();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }

    private void deleteBook() {
        String idBook = tvId.getText().toString();
        ApiService.apiService.deleteBookByID(idBook).enqueue(new Callback<BookRespone>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<BookRespone> call, Response<BookRespone> response) {
                BookRespone res = response.body();
                if (response.code() == 400) {
                    Toast.makeText(BookActivity.this, "Vui lòng xóa detail bill trước!", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    assert res != null;
                    Toast.makeText(BookActivity.this, "Xóa thành công!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(BookActivity.this, ListBookActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<BookRespone> call, Throwable throwable) {
                Toast.makeText(BookActivity.this, "Xóa thất bại!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateBook() {
        String idBook = tvId.getText().toString();
        String name = tvName.getText().toString();
        String idType = tvIdType.getText().toString();
        String price = tvPrice.getText().toString();
        String quantity = tvQuantity.getText().toString();
        String creator = tvCreator.getText().toString();
        String producer = tvProducer.getText().toString();
        BookRespone bookRespone = new BookRespone(idBook, name, idType, price, quantity, creator, producer);
        if (name.equals("") || price.equals("") || quantity.equals("") || creator.equals("") || producer.equals("")) {
            Toast.makeText(BookActivity.this, "Vui lòng nhập đủ thông tin!", Toast.LENGTH_SHORT).show();
        } else {
            ApiService.apiService.updateBook(bookRespone, idBook).enqueue(new Callback<BookRespone>() {
                @Override
                public void onResponse(Call<BookRespone> call, Response<BookRespone> response) {
                }

                @Override
                public void onFailure(Call<BookRespone> call, Throwable throwable) {
                }
            });
        }
    }
}
