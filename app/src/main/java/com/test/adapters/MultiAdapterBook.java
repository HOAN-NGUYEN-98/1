package com.test.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.test.R;
import com.test.activities.BillDetailActivity;
import com.test.models.Book;

import java.util.ArrayList;
import java.util.List;

public class MultiAdapterBook extends RecyclerView.Adapter<MultiAdapterBook.MultiViewHolder> {

    private Context context;
    private ArrayList<Book> books;

    public MultiAdapterBook(Context context, ArrayList<Book> books) {
        this.context = context;
        this.books = books;
    }

    public void setEmployees(ArrayList<Book> employees) {
        this.books = employees;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MultiViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_sach, viewGroup, false);
        return new MultiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MultiViewHolder multiViewHolder, int position) {
        multiViewHolder.bind(books.get(position));
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    class MultiViewHolder extends RecyclerView.ViewHolder {
        TextView tvQ;
        private TextView textView, tvPrice, tvQuantity, textViewId;
        private ImageView imageView, imgAdd, imgMinus;

        MultiViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewId = itemView.findViewById(R.id.tvBookID);
            textView = itemView.findViewById(R.id.tvBookName);
            tvPrice = itemView.findViewById(R.id.tvBookPrice);
            tvQuantity = itemView.findViewById(R.id.tvSo_Luong);
            imageView = itemView.findViewById(R.id.imageView_tick);
            imgAdd = itemView.findViewById(R.id.add_quan);
            imgMinus = itemView.findViewById(R.id.minus_quan);
            tvQ = itemView.findViewById(R.id.change_quantity);
        }

        void bind(final Book book) {
            tvQ.setVisibility(book.isChecked() ? View.VISIBLE : View.GONE);
            imageView.setVisibility(book.isChecked() ? View.VISIBLE : View.GONE);
            imgAdd.setVisibility(book.isChecked() ? View.VISIBLE : View.GONE);
            imgMinus.setVisibility(book.isChecked() ? View.VISIBLE : View.GONE);
            imgMinus.setVisibility(book.isChecked() ? View.VISIBLE : View.GONE);

            Log.d("TAG", "bind: " + tvQuantity.getText().toString());

            tvPrice.setText(String.valueOf(book.getPrice()));
            textView.setText(book.getName());
            textViewId.setText(book.getIdBook());
            tvQuantity.setText(String.valueOf(book.getQuantity()));
            int s = Integer.parseInt(tvQuantity.getText().toString());

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    book.setChecked(!book.isChecked());
                    imageView.setVisibility(book.isChecked() ? View.VISIBLE : View.GONE);
                    imgAdd.setVisibility(book.isChecked() ? View.VISIBLE : View.GONE);
                    int m = Integer.parseInt(tvQ.getText().toString());

                    imgMinus.setVisibility(book.isChecked() ? View.VISIBLE : View.GONE);
                    tvQ.setVisibility(book.isChecked() ? View.VISIBLE : View.GONE);
                    if (tvQ.getText().toString().equals("0")) {
                        Toast.makeText(context, "Nhập số lượng mua", Toast.LENGTH_SHORT).show();
                    }
                    if (m < 0 || m == 0) {

                        imgMinus.setVisibility(View.INVISIBLE);
                        book.setQuantity("0");

                    }
                    imgAdd.setOnClickListener(new View.OnClickListener() {

                        int chane;

                        @Override
                        public void onClick(View v) {

                            chane = Integer.parseInt(tvQ.getText().toString());
                            chane++;
                            tvQ.setText(String.valueOf(chane));
                            int n = Integer.parseInt(tvQ.getText().toString());
                            if (n > 0) {
                                imgMinus.setVisibility(View.VISIBLE);
                            }

                            if (n == s) {
                                Toast.makeText(context, "Sản phẩm đã hết, vui lòng nhập thêm!", Toast.LENGTH_LONG).show();
                                imgAdd.setVisibility(View.INVISIBLE);
                            }
                            book.setQuantity(tvQ.getText().toString());
                        }
                    });

                    imgMinus.setOnClickListener(new View.OnClickListener() {
                        int chane;

                        @Override
                        public void onClick(View v) {
                            chane = Integer.parseInt(tvQ.getText().toString());
                            chane--;
                            tvQ.setText(String.valueOf(chane));
                            int n = Integer.parseInt(tvQ.getText().toString());
                            if (n < 1) {
                                imgMinus.setVisibility(View.INVISIBLE);
                            }
                            if (n == 0 || n < s) {
                                imgAdd.setVisibility(View.VISIBLE);
                            }
                            if (n == 0) {
                                Toast.makeText(context, "Vui lòng nhập số lượng!", Toast.LENGTH_LONG).show();
                            }
                            book.setQuantity(tvQ.getText().toString());


                        }
                    });
                }
            });
        }
    }

    public ArrayList<Book> getAll() {
        return books;
    }

    public ArrayList<Book> getSelected() {
        ArrayList<Book> selected = new ArrayList<>();
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).isChecked()) {
                selected.add(books.get(i));
            }
        }
        return selected;
    }

}