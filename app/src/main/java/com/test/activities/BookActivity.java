package com.test.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.test.R;
import com.test.api.ApiService;
import com.test.models.Book;
import com.test.models.BookRespone;
import com.test.models.TypeBookRespone;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView tvId, tvName, tvCreator, tvProducer, tvPrice, tvQuantity, tvIdType, tvNameType;
    Button del, back, upd;
    Spinner spin;
    List<String> list = new ArrayList<>();

    String dest;
    List<String> list1 = new ArrayList<>();

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
        tvNameType = findViewById(R.id.ed_name_type_sua);
        assert book != null;
        tvId.setText(book.getIdBook());
        tvName.setText(book.getName());
        tvCreator.setText(book.getCreator());
        tvProducer.setText(book.getProducer());
        tvPrice.setText(book.getPrice());
        tvQuantity.setText(book.getQuantity());
        tvIdType.setText(book.getIdType());
        tvNameType.setText(book.getNameType());

        spin = (Spinner) findViewById(R.id.spinner1);
        spin.setOnItemSelectedListener(this);
        getAllType();

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
        Book bookRespone = new Book(idBook, idType, name, creator, producer, price, quantity);
        if (name.equals("") || price.equals("") || quantity.equals("") || creator.equals("") || producer.equals("")) {
            Toast.makeText(BookActivity.this, "Vui lòng nhập đủ thông tin!", Toast.LENGTH_SHORT).show();
        } else {
            ApiService.apiService.updateBook(bookRespone, idBook).enqueue(new Callback<Book>() {
                @Override
                public void onResponse(Call<Book> call, Response<Book> response) {
                   if(response.code()==200){
                       Toast.makeText(BookActivity.this, "Sửa thành công!", Toast.LENGTH_SHORT).show();
                       Intent intent = new Intent(BookActivity.this, ListBookActivity.class);
                       startActivity(intent);
                       finish();
                   }
                   else {
                       Toast.makeText(BookActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                   }
                }

                @Override
                public void onFailure(Call<Book> call, Throwable throwable) {
                    Toast.makeText(BookActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    public void getAllType() {
        ApiService.apiService.getTypeBook().enqueue(new Callback<List<TypeBookRespone>>() {
            @Override
            public void onResponse(Call<List<TypeBookRespone>> call, Response<List<TypeBookRespone>> response) {
                List<TypeBookRespone> res = response.body();
                for (int i = 0; i < res.size(); i++) {
                    list.add(res.get(i).getNameType());


                }


                ArrayAdapter aa = new ArrayAdapter(BookActivity.this, android.R.layout.simple_spinner_item, list);
                aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                //Setting the ArrayAdapter data on the Spinner
                spin.setAdapter(aa);

            }

            @Override
            public void onFailure(Call<List<TypeBookRespone>> call, Throwable throwable) {
                Toast.makeText(BookActivity.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String text = spin.getSelectedItem().toString();
        ApiService.apiService.detailType(text).enqueue(new Callback<TypeBookRespone>() {
            @Override
            public void onResponse(Call<TypeBookRespone> call, Response<TypeBookRespone> response) {
                TypeBookRespone res = response.body();
                assert res != null;
                tvIdType.setText(String.valueOf(res.getIdType()));
                //tvNameType.setText(String.valueOf(res.getNameType()));
            }

            @Override
            public void onFailure(Call<TypeBookRespone> call, Throwable throwable) {
                Toast.makeText(BookActivity.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
