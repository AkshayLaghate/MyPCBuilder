package com.indcoders.mypcbuilder.Utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.indcoders.mypcbuilder.Model.Ram;
import com.indcoders.mypcbuilder.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Akki on 25/07/16.
 */

public class RamAdapter extends RecyclerView.Adapter<RamAdapter.RamViewHolder> {

    private final LayoutInflater inflator;
    private final List<Ram> itemList;
    Context c;

    private RamAdapter.OnSelectedListener onSelectedListener;

    public RamAdapter(Context context, ArrayList<Ram> list) {
        inflator = LayoutInflater.from(context);
        c = context;
        itemList = list;
    }

    @Override
    public RamAdapter.RamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflator.inflate(R.layout.ram_item, parent, false);
        RamAdapter.RamViewHolder holder = new RamAdapter.RamViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(RamAdapter.RamViewHolder holder, int position) {

        holder.tvRamName.setText(itemList.get(position).getName());
        holder.tvRamMemory.setText("Type: " + itemList.get(position).getMemory());
        holder.tvRamPrice.setText("Rs." + itemList.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void setOnSelectedListener(RamAdapter.OnSelectedListener mListener) {
        onSelectedListener = mListener;
    }

    public interface OnSelectedListener {
        void onSelected(Ram ram);
    }

    class RamViewHolder extends RecyclerView.ViewHolder {

        TextView tvRamName, tvRamMemory, tvRamPrice;
        Button bSelectRam;

        public RamViewHolder(View itemView) {
            super(itemView);

            tvRamName = (TextView) itemView.findViewById(R.id.tvRamItemName);
            tvRamMemory = (TextView) itemView.findViewById(R.id.tvRamItemMemory);
            tvRamPrice = (TextView) itemView.findViewById(R.id.tvRamItemPrice);


            bSelectRam = (Button) itemView.findViewById(R.id.bSelectItemRam);

            bSelectRam.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onSelectedListener.onSelected(itemList.get(getAdapterPosition()));
                }
            });
        }
    }
}
