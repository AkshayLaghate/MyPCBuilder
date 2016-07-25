package com.indcoders.mypcbuilder.Utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.indcoders.mypcbuilder.Model.Motherboard;
import com.indcoders.mypcbuilder.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Akki on 25/07/16.
 */

public class MotherboardAdapter extends RecyclerView.Adapter<MotherboardAdapter.MotherboardViewHolder> {

    private final LayoutInflater inflator;
    private final List<Motherboard> itemList;
    Context c;

    private OnSelectedListener onSelectedListener;

    public MotherboardAdapter(Context context, ArrayList<Motherboard> list) {
        inflator = LayoutInflater.from(context);
        c = context;
        itemList = list;
    }

    @Override
    public MotherboardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflator.inflate(R.layout.motherboard_item, parent, false);
        MotherboardViewHolder holder = new MotherboardViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(MotherboardViewHolder holder, int position) {

        holder.tvMbName.setText(itemList.get(position).getName());
        holder.tvMbForm.setText("Form: " + itemList.get(position).getForm());
        holder.tvMbSocket.setText("Socket: " + itemList.get(position).getSocket());
        holder.tvMbChipset.setText("Chipset: " + itemList.get(position).getChipset());
        holder.tvMbPrice.setText("Rs." + itemList.get(position).getPrice());

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void setOnSelectedListener(OnSelectedListener mListener) {
        onSelectedListener = mListener;
    }

    public interface OnSelectedListener {
        void onSelected(Motherboard motherboard);
    }

    class MotherboardViewHolder extends RecyclerView.ViewHolder {

        TextView tvMbName, tvMbForm, tvMbChipset, tvMbSocket, tvMbPrice;
        Button bSelectMb;

        public MotherboardViewHolder(View itemView) {
            super(itemView);

            tvMbName = (TextView) itemView.findViewById(R.id.tvMbItemName);
            tvMbForm = (TextView) itemView.findViewById(R.id.tvMbItemForm);
            tvMbChipset = (TextView) itemView.findViewById(R.id.tvMbItemChipset);
            tvMbSocket = (TextView) itemView.findViewById(R.id.tvMbItemSocket);
            tvMbPrice = (TextView) itemView.findViewById(R.id.tvMbItemPrice);

            bSelectMb = (Button) itemView.findViewById(R.id.bSelectItemMb);

            bSelectMb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onSelectedListener.onSelected(itemList.get(getAdapterPosition()));
                }
            });
        }
    }
}
