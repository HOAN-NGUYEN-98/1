package com.test;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.test.models.Book;

public class BookActivity extends AppCompatActivity {
    TextView tvId, tvName,tvCreator,tvProducer,tvPrice,tvQuantity;
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
        assert book != null;
        tvId.setText(book.getIdBook());
        tvName.setText(book.getName());
        tvCreator.setText(book.getCreator());
        tvProducer.setText(book.getProducer());
        tvPrice.setText(book.getPrice());
        tvQuantity.setText(book.getQuantity());



    }
}
