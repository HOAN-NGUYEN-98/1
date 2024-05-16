package com.test;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.test.models.TypeBookRespone;

public class TypeBookDetailActivity extends AppCompatActivity {
    TextView tvId,tvName,tvMo,tvVi;

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
        tvName = findViewById(R.id.tvTenTheLoai);
        tvMo = findViewById(R.id.tvMoTa);
        tvVi = findViewById(R.id.tvVitri);
        assert bill != null;
        tvId.setText(bill.getIdType());
        tvName.setText(bill.getNameType());
        tvMo.setText(bill.getDescribe());
        tvVi.setText(bill.getLocation());
    }
}
