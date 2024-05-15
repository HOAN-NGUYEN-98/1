package com.test;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.test.models.Bill;

public class BillDetailActivity extends AppCompatActivity {

    TextView tvdate,tvid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.detail_bill_activitty);

        Bundle bundle = getIntent().getExtras();
        if(bundle==null){
            return;
        }
        Bill bill=(Bill) bundle.get("object_bill");

        tvid = findViewById(R.id.tv_id_bill);
        assert bill != null;
        tvid.setText(bill.getIdBill());
        tvdate = findViewById(R.id.tv_date);
        tvdate.setText(bill.getDateOfBuy());
    }
}