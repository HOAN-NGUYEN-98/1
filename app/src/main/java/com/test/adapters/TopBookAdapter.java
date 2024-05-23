package com.test.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.test.R;
import com.test.models.TopBook;
import com.test.my_interface.IClickItemBillListener;

import java.util.List;

public class TopBookAdapter extends RecyclerView.Adapter<TopBookAdapter.TopBookViewHolder> {
    List<TopBook> billList;

    public TopBookAdapter(List<TopBook> billList) {
        this.billList = billList;
    }

    @NonNull
    @Override
    public TopBookAdapter.TopBookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_top_book, parent, false);
        return new TopBookAdapter.TopBookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopBookAdapter.TopBookViewHolder holder, int position) {
        final TopBook bill = billList.get(position);
        if (bill == null) {
            return;
        }
        holder.tvId.setText(bill.getIdBook());
        holder.tvName.setText(bill.getNameBook());
        holder.tvQuan.setText(bill.getQuantity());
//        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                iClickItemBillListener.conClickItemBill(bill);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        if (billList != null) {
            return billList.size();
        }
        return 0;
    }

    public class TopBookViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout layoutItem;
        private TextView tvId, tvName,tvQuan;

        public TopBookViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutItem = itemView.findViewById(R.id.layout_item_top_book);
            tvId = itemView.findViewById(R.id.tvBookIDTop);
            tvName = itemView.findViewById(R.id.tvBookNameTop);
            tvQuan = itemView.findViewById(R.id.tvSoLuongTop);
        }
    }

}
