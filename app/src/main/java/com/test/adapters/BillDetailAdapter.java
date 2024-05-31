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

import java.util.ArrayList;
import java.util.List;

public class BillDetailAdapter extends RecyclerView.Adapter<BillDetailAdapter.DetailViewHolder> {
    private Context context;
    private ArrayList<BillDetailRespone> books;

    public BillDetailAdapter(Context context, ArrayList<BillDetailRespone> books) {
        this.context = context;
        this.books = books;
    }

    public void setEmployees(ArrayList<BillDetailRespone> employees) {
        this.books = employees;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BillDetailAdapter.DetailViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_sach, viewGroup, false);
        return new BillDetailAdapter.DetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BillDetailAdapter.DetailViewHolder multiViewHolder, int position) {
        multiViewHolder.bind(books.get(position));
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    class DetailViewHolder extends RecyclerView.ViewHolder {
        TextView tvQ;
        private TextView textView, tvPrice, tvQuantity, textViewId;
        private ImageView imageView, imgAdd, imgMinus;

        DetailViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewId = itemView.findViewById(R.id.tvBookID);
            textView = itemView.findViewById(R.id.tvBookName);
            tvPrice = itemView.findViewById(R.id.tvBookPrice);
            tvQuantity = itemView.findViewById(R.id.tvSoLuong);
            imageView = itemView.findViewById(R.id.imageView_tick);
            imgAdd = itemView.findViewById(R.id.add_quan);
            imgMinus = itemView.findViewById(R.id.minus_quan);
            tvQ = itemView.findViewById(R.id.change_quantity);
        }

        void bind(final BillDetailRespone billDetailRespone) {




        }
    }

    public ArrayList<BillDetailRespone> getAll() {
        return books;
    }

}