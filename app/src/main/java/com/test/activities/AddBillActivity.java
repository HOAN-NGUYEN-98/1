package com.test.activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.net.ParseException;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.test.R;
import com.test.adapters.BookAdapter;
import com.test.adapters.MultiAdapterBook;
import com.test.adapters.TotalMoneyAdapter;
import com.test.api.ApiService;
import com.test.models.Bill;
import com.test.models.Book;
import com.test.my_interface.IClickBookListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddBillActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    TextView tv_TongThanhToan, tvID,tvDate;
    private RecyclerView recyclerView;
    RecyclerView rcvData;
    private MultiAdapterBook adapter;
    private Button btnGetSelected;
    EditText edt_IdBill, edt_CreateDate;
    Button btn_SelectDate;
    ArrayList<Book> bookList;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        bookList = new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_hoa_don);
        edt_CreateDate = findViewById(R.id.edt_date_buy);
        edt_IdBill = findViewById(R.id.edt_id_bill);

        btn_SelectDate = findViewById(R.id.picDate);
        rcvData = findViewById(R.id.rcvData_book);
        btnGetSelected = findViewById(R.id.btn_them_hoa_don);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvData.setLayoutManager(linearLayoutManager);
        getListBook();
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rcvData.addItemDecoration(itemDecoration);//nhin dep hon

        adapter = new MultiAdapterBook(this,bookList);
        btnGetSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                multiClick();
            }
        });

        btn_SelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                importDatePicker();
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

    void getListBook() {
        ApiService.apiService.getListBook().enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                List<Book> json = response.body();
                assert json != null;
                bookList.addAll(json);
                rcvData.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable throwable) {
                Toast.makeText(AddBillActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void multiClick() {
        String id=edt_IdBill.getText().toString();
        String date=edt_CreateDate.getText().toString();
        setContentView(R.layout.activity_thanh_toan);
        tv_TongThanhToan = findViewById(R.id.textViewTongThanhToan);
        rcvData = findViewById(R.id.recyclerView_Data);
        tvDate=findViewById(R.id.textViewDateCreate);
        tvID=findViewById(R.id.textViewIDBill);
        int tt;
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < adapter.getSelected().size(); i++) {

            ArrayList<Book> empppp = adapter.getSelected();
            tvID.setText(id);
            tvDate.setText(date);

            int pr = Integer.parseInt(empppp.get(i).getPrice());
            int qu = Integer.parseInt(empppp.get(i).getQuantity());
            tt = pr * qu;
            numbers.add(tt);
            int sum = 0;
            for (int number : numbers) {
                sum += number;
                tv_TongThanhToan.setText(String.valueOf(sum));
            }

            // Tạo một adapter mới với list các item được chọn

            TotalMoneyAdapter selectedItemsAdapter = new TotalMoneyAdapter(this, empppp);
            rcvData.setLayoutManager(new LinearLayoutManager(this));
            rcvData.setAdapter(selectedItemsAdapter);
        }



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
