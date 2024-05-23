package com.test.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.test.R;
import com.test.models.Bill;
import com.test.models.BillDetailRespone;

import java.util.List;

public class BillDetailAdapter extends RecyclerView.Adapter<BillDetailAdapter.BillDetailViewHolder> {
    List<BillDetailRespone> billList;

    public BillDetailAdapter(List<BillDetailRespone> billList) {
        this.billList = billList;
    }

    @NonNull
    @Override
    public BillDetailAdapter.BillDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail_bill, parent, false);
        return new BillDetailAdapter.BillDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BillDetailAdapter.BillDetailViewHolder holder, int position) {
        final BillDetailRespone bill = billList.get(position);
        if (bill == null) {
            return;
        }
        //holder.imgAvatar.setImageResource(bill.getIdBill());
        holder.tvIdBill.setText(bill.getIdBill());
        holder.tvIdBook.setText(bill.getIdBook());
        holder.tvPrice.setText(bill.getPrice());
        holder.tvIdDetailBill.setText(bill.getIdDetailBill());
        holder.tvQuantity.setText(bill.getQuantitySell());



    }

    @Override
    public int getItemCount() {
        if (billList != null) {
            return billList.size();
        }
        return 0;
    }

    public class BillDetailViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout layoutItem;
        private TextView tvIdBook, tvQuantity,tvIdBill,tvIdDetailBill,tvPrice;

        public BillDetailViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutItem = itemView.findViewById(R.id.item_detail_bill);
            tvIdBook = itemView.findViewById(R.id.tv_id_book);
            tvIdBill = itemView.findViewById(R.id.tv_id_bill);
            tvIdDetailBill = itemView.findViewById(R.id.tv_id_detail_bill);
            tvQuantity = itemView.findViewById(R.id.tv_quantity);
            tvPrice = itemView.findViewById(R.id.tv_price);

        }
    }

}
