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
import com.test.models.TypeBookRespone;
import com.test.my_interface.IClickTypeBookListener;

import java.util.List;

public class TypeBookAdapter  extends RecyclerView.Adapter<TypeBookAdapter.TypeBookViewHolder> {
    List<TypeBookRespone> typeBookResponeList;
    private IClickTypeBookListener iClickItemTypeBookListener;

    public TypeBookAdapter(List<TypeBookRespone> typeBookResponeList1, IClickTypeBookListener listener) {
        this.iClickItemTypeBookListener = listener;
        this.typeBookResponeList = typeBookResponeList1;
    }

    @NonNull
    @Override
    public TypeBookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_theloai, parent, false);
        return new TypeBookAdapter.TypeBookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TypeBookViewHolder holder, int position) {

        final TypeBookRespone typeBookRespone = typeBookResponeList.get(position);
        if (typeBookRespone == null) {
            return;
        }
        //holder.imgAvatar.setImageResource(bill.getIdBill());
        holder.tvId.setText(typeBookRespone.getIdType());
        holder.name.setText(typeBookRespone.getNameType());
        holder.location.setText(typeBookRespone.getLocation());
        holder.describe.setText(typeBookRespone.getDescribe());
        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItemTypeBookListener.conClickItemTypeBook(typeBookRespone);
            }
        });
    }


    @Override
    public int getItemCount() {
        if (typeBookResponeList != null) {
            return typeBookResponeList.size();
        }
        return 0;
    }

    public class TypeBookViewHolder extends RecyclerView.ViewHolder {

        private RelativeLayout layoutItem;
        private ImageView imgAvatar;
        private TextView tvId, name,location,describe;

        public TypeBookViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutItem = itemView.findViewById(R.id.layout_item_the_loai);
            imgAvatar = itemView.findViewById(R.id.ivIcon);
            tvId = itemView.findViewById(R.id.tvMaTheLoai);
            name = itemView.findViewById(R.id.tvTenTheLoai);
            location = itemView.findViewById(R.id.tvVitri);
            describe = itemView.findViewById(R.id.tvMoTa);
        }
    }
}
