package com.test.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.test.R;
import com.test.models.Bill;
import com.test.my_interface.IClickItemBillListener;

import java.util.List;

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.BillViewHolder> {
    List<Bill> billList;
    private IClickItemBillListener iClickItemBillListener;

    public BillAdapter(List<Bill> billList, IClickItemBillListener listener) {
        this.iClickItemBillListener = listener;
        this.billList = billList;
    }

    @NonNull
    @Override
    public BillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bill, parent, false);
        return new BillViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BillViewHolder holder, int position) {
        final Bill bill = billList.get(position);
        if (bill == null) {
            return;
        }
        //holder.imgAvatar.setImageResource(bill.getIdBill());
        holder.tvId.setText(bill.getIdBill());
        holder.tvDateOfBuy.setText(bill.getDateOfBuy());
        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItemBillListener.conClickItemBill(bill);
            }
        });
    }


    @Override
    public int getItemCount() {
        if (billList != null) {
           return billList.size();
        }
        return 0;
    }

    public class BillViewHolder extends RecyclerView.ViewHolder {

        private RelativeLayout layoutItem;
        private ImageView imgAvatar;
        private TextView tvId, tvDateOfBuy;

        public BillViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutItem = itemView.findViewById(R.id.layout_item);
            imgAvatar = itemView.findViewById(R.id.img_avatar);
            tvId = itemView.findViewById(R.id.tv_idBill);
            tvDateOfBuy = itemView.findViewById(R.id.tv_dateOfBuy);
        }
    }
}
