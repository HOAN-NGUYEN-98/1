package com.test.activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.net.ParseException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.test.R;
import com.test.api.ApiService;
import com.test.models.Bill;

import java.util.Calendar;
import java.util.GregorianCalendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddBillActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    EditText edt_IdBill, edt_CreateDate;
    Button btn_SelectDate, btn_AddBill;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_hoa_don);
        edt_CreateDate = findViewById(R.id.edt_date_buy);
        edt_IdBill = findViewById(R.id.edt_id_bill);
        btn_AddBill = findViewById(R.id.btn_them_hoa_don);
        btn_SelectDate = findViewById(R.id.picDate);

        btn_SelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                importDatePicker();
            }
        });
        btn_AddBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idBill = edt_IdBill.getText().toString();
                String dateOfBuy = edt_CreateDate.getText().toString();
                Bill bill = new Bill(idBill, dateOfBuy);
                ApiService.apiService.postBill(bill).enqueue(new Callback<Bill>() {
                    @Override
                    public void onResponse(Call<Bill> call, Response<Bill> response) {
                        Bill bill1 = response.body();
                        if (response.code() == 400) {
                            Toast.makeText(AddBillActivity.this, "Bill đã tồn tại!", Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            assert bill1 != null;
                            Toast.makeText(AddBillActivity.this, "Thêm thành công!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(AddBillActivity.this, AddDetailBillActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("add_bill", bill1);
                            intent.putExtras(bundle);
                            startActivity(intent);
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<Bill> call, Throwable throwable) {
                        Toast.makeText(AddBillActivity.this, "Thêm thất bại!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void importDatePicker() {
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.show(getSupportFragmentManager(), "date");
    }

    public static class DatePickerFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), (DatePickerDialog.OnDateSetListener) getActivity(), year, month, day);
        }

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar cal = new GregorianCalendar(year, month, dayOfMonth);
        setDate(cal);

    }

    private void setDate(final Calendar calendar) {
        edt_CreateDate.setText(sdf.format(calendar.getTime()));
    }

}
