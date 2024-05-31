package com.test.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.test.R;
import com.test.models.Book;
import com.test.my_interface.IClickBookListener;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {
    List<Book> bookList;
    private IClickBookListener iClickBookListener;

    public BookAdapter(List<Book> bookList, IClickBookListener listener) {
        this.iClickBookListener = listener;

        this.bookList = bookList;
    }

    @NonNull
    @Override
    public BookAdapter.BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sach, parent, false);

        return new BookAdapter.BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookAdapter.BookViewHolder holder, int position) {
        final Book book = bookList.get(position);
        if (book == null) {
            return;
        }
        holder.tvId.setText(book.getIdBook());
        holder.tvBookName.setText(book.getName());
        holder.tvPrice.setText(book.getPrice());
        holder.tvSoLuong.setText(book.getQuantity());
        holder.tvQ.setVisibility(book.isChecked() ? View.VISIBLE : View.GONE);

        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickBookListener.conClickItemBook(book);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (bookList != null) {
            return bookList.size();
        }
        return 0;
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout layoutItem;
        private TextView tvBookName, tvSoLuong, tvPrice, tvId;
        TextView tvQ;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            tvQ = itemView.findViewById(R.id.change_quantity);
            layoutItem = itemView.findViewById(R.id.layout_item_book);
            tvId = itemView.findViewById(R.id.tvBookID);
            tvBookName = itemView.findViewById(R.id.tvBookName);
            tvPrice = itemView.findViewById(R.id.tvBookPrice);
            tvSoLuong = itemView.findViewById(R.id.tvSoLuong);

        }
    }

}
