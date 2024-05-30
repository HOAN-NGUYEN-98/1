package com.test.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.test.R;
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
            tvQuantity = itemView.findViewById(R.id.tvSoLuong);
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

            tvPrice.setText(String.valueOf(book.getPrice()));
            textView.setText(book.getName());
            textViewId.setText(book.getIdBook());
            tvQuantity.setText(String.valueOf(book.getQuantity()));

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    book.setChecked(!book.isChecked());
                    imageView.setVisibility(book.isChecked() ? View.VISIBLE : View.GONE);
                    imgAdd.setVisibility(book.isChecked() ? View.VISIBLE : View.GONE);
                    imgMinus.setVisibility(book.isChecked() ? View.VISIBLE : View.GONE);
                    tvQ.setVisibility(book.isChecked() ? View.VISIBLE : View.GONE);
                    
                    if(tvQ.getText().toString().equals("0")){
                        Toast.makeText(context, "Nhập số lượng mua", Toast.LENGTH_SHORT).show();
                    }
                    imgAdd.setOnClickListener(new View.OnClickListener() {

                        int chane;
                        @Override
                        public void onClick(View v) {

                            chane = Integer.parseInt(tvQ.getText().toString());
                            chane++;
                            tvQ.setText(String.valueOf(chane));
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
                            book.setQuantity(tvQ.getText().toString());
                            if (chane < 1) {
                                imgMinus.setVisibility(View.INVISIBLE);
                            }
                            imgMinus.setVisibility(View.VISIBLE);
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