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
import com.test.api.ApiService;
import com.test.models.Bill;
import com.test.models.BillDetailRespone;
import com.test.my_interface.IClickItemBillListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailBillAdapter extends RecyclerView.Adapter<DetailBillAdapter.DetailBillViewHolder>{
    List<Bill> billList;
    private IClickItemBillListener iClickItemBillListener;

    public DetailBillAdapter(List<Bill> billList, IClickItemBillListener listener) {
        this.iClickItemBillListener = listener;
        this.billList = billList;
    }

    @NonNull
    @Override
    public DetailBillAdapter.DetailBillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail_bill, parent, false);
        return new DetailBillAdapter.DetailBillViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailBillAdapter.DetailBillViewHolder holder, int position) {
        final Bill bill = billList.get(position);
        if (bill == null) {
            return;
        }
        ApiService.apiService.detailBillByIdBill("").enqueue(new Callback<BillDetailRespone>() {
            @Override
            public void onResponse(Call<BillDetailRespone> call, Response<BillDetailRespone> response) {

            }

            @Override
            public void onFailure(Call<BillDetailRespone> call, Throwable throwable) {

            }
        });

        //holder.imgAvatar.setImageResource(bill.getIdBill());
        holder.tvIdDetailBill.setText(bill.getIdBill());

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

    public class DetailBillViewHolder extends RecyclerView.ViewHolder {

        private RelativeLayout layoutItem;

        private TextView tvIdDetailBill, tvIdBook,tvIdBill,tvQuantity,tvPrice;

        public DetailBillViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutItem = itemView.findViewById(R.id.item_detail_bill);
            tvIdDetailBill = itemView.findViewById(R.id.tv_id_detail_bill);
            tvIdBook = itemView.findViewById(R.id.tv_id_book);
            tvIdBill = itemView.findViewById(R.id.tv_id_bill);
            tvQuantity = itemView.findViewById(R.id.tv_quantity);
            tvPrice = itemView.findViewById(R.id.tv_price);
        }
    }
}

