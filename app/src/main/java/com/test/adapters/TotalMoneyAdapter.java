package com.test.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.test.R;
import com.test.models.Bill;
import com.test.models.Book;

import java.util.ArrayList;

public class TotalMoneyAdapter extends RecyclerView.Adapter<TotalMoneyAdapter.MultiViewHolder> {

    private Context context;
    private ArrayList<Book> employees;

    public TotalMoneyAdapter(Context context, ArrayList<Book> employees) {
        this.context = context;
        this.employees = employees;
    }


    public void setEmployees(ArrayList<Book> employees) {
        this.employees = employees;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MultiViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_thanhtoan, viewGroup, false);
        return new MultiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MultiViewHolder multiViewHolder, int position) {
        multiViewHolder.bind(employees.get(position));
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    class MultiViewHolder extends RecyclerView.ViewHolder {

        private TextView textView, tvPrice, tvQuantity, money;


        MultiViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textViewNameThanhToan);
            tvPrice = itemView.findViewById(R.id.textViewPriceThanhToan);
            tvQuantity = itemView.findViewById(R.id.textViewQuantityThanhToan);
            money = itemView.findViewById(R.id.textViewThanhTienThanhToan);
        }

        void bind(final Book employee) {
            tvPrice.setText(String.valueOf(employee.getPrice()));
            tvQuantity.setText(String.valueOf(employee.getQuantity()));
            textView.setText(employee.getName());


            String name=employee.getName();
            String price=String.valueOf(employee.getPrice());
            String quan=String.valueOf(employee.getQuantity());



            int pr = Integer.parseInt(String.valueOf(employee.getPrice()));
            int qu = Integer.parseInt(String.valueOf(employee.getQuantity()));
            int total = pr * qu;
            money.setText(String.valueOf(total));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    employee.setChecked(!employee.isChecked());
                }
            });
        }
    }

    public ArrayList<Book> getAll() {
        return employees;
    }

    public ArrayList<Book> getSelected() {
        ArrayList<Book> selected = new ArrayList<>();
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).isChecked()) {
                selected.add(employees.get(i));
            }
        }
        return selected;
    }

}
