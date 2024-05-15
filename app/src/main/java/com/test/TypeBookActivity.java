package com.test;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.test.models.Bill;
import com.test.models.TypeBookRespone;

public class TypeBookActivity extends AppCompatActivity {
    TextView tvId,tvName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.item_theloai);

        Bundle bundle = getIntent().getExtras();
        if(bundle==null){
            return;
        }
        TypeBookRespone bill=(TypeBookRespone) bundle.get("object_type");

        tvId = findViewById(R.id.tvMaTheLoai);
        assert bill != null;
        tvId.setText(bill.getIdType());
        tvName = findViewById(R.id.tvTenTheLoai);
        tvName.setText(bill.getNameType());
    }
}
