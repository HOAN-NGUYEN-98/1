package com.test.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.test.R;
import com.test.models.Bill;
import com.test.models.BillDetailRespone;
import com.test.models.Book;
import com.test.models.Detail;

import java.util.ArrayList;
import java.util.List;

public class BillDetailAdapter extends RecyclerView.Adapter<BillDetailAdapter.DetailViewHolder> {
    private Context context;
    private List<Detail> books;

    public BillDetailAdapter(Context context, List<Detail> books) {
        this.context = context;
        this.books = books;
    }

    public void setEmployees(ArrayList<Detail> employees) {
        this.books = employees;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BillDetailAdapter.DetailViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_detail_bill, viewGroup, false);
        return new BillDetailAdapter.DetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BillDetailAdapter.DetailViewHolder holder, int position) {
        holder.bind(books.get(position));
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    class DetailViewHolder extends RecyclerView.ViewHolder {

        private TextView  tvPrice, tvQuantitySell, textViewName;
        private RelativeLayout layoutItem;
        DetailViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.tv_name_book_detail);
            tvPrice = itemView.findViewById(R.id.tv_price);
            tvQuantitySell = itemView.findViewById(R.id.tv_quantity);

        }

        void bind(final Detail billDetailRespone) {
            textViewName.setText(String.valueOf(billDetailRespone.getName()));
            tvPrice.setText(String.valueOf(billDetailRespone.getPrice()));
            tvQuantitySell.setText(String.valueOf(billDetailRespone.getQuantitySell()));
        }
    }

    public List<Detail> getAll() {
        return books;
    }

}