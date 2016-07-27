package com.indcoders.mypcbuilder.Utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.indcoders.mypcbuilder.Model.PSU;
import com.indcoders.mypcbuilder.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Akki on 27/07/16.
 */

public class PSUAdapter extends RecyclerView.Adapter<PSUAdapter.PSUViewHolder> {

    private final LayoutInflater inflator;
    private final List<PSU> itemList;
    Context c;

    private PSUAdapter.OnSelectedListener onSelectedListener;

    public PSUAdapter(Context context, ArrayList<PSU> list) {
        inflator = LayoutInflater.from(context);
        c = context;
        itemList = list;
    }

    @Override
    public PSUAdapter.PSUViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflator.inflate(R.layout.psu_item, parent, false);
        PSUAdapter.PSUViewHolder holder = new PSUAdapter.PSUViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(PSUAdapter.PSUViewHolder holder, int position) {

        holder.tvPsuName.setText(itemList.get(position).getName());
        holder.tvPsuPower.setText("Power: " + itemList.get(position).getPower());
        holder.tvPsuEfficiency.setText("Efficiency: " + itemList.get(position).getEfficiency());
        holder.tvPsuPrice.setText("Rs." + itemList.get(position).getPrice());

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void setOnSelectedListener(PSUAdapter.OnSelectedListener mListener) {
        onSelectedListener = mListener;
    }

    public interface OnSelectedListener {
        void onSelected(PSU psu);
    }

    class PSUViewHolder extends RecyclerView.ViewHolder {

        TextView tvPsuName, tvPsuPower, tvPsuEfficiency, tvPsuPrice;
        Button bSelectPsu;

        public PSUViewHolder(View itemView) {
            super(itemView);

            tvPsuName = (TextView) itemView.findViewById(R.id.tvPSUItemName);
            tvPsuPower = (TextView) itemView.findViewById(R.id.tvPSUItemPower);
            tvPsuEfficiency = (TextView) itemView.findViewById(R.id.tvPSUItemEfficiency);
            tvPsuPrice = (TextView) itemView.findViewById(R.id.tvPSUItemPrice);

            bSelectPsu = (Button) itemView.findViewById(R.id.bSelectItemPSU);

            bSelectPsu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onSelectedListener.onSelected(itemList.get(getAdapterPosition()));
                }
            });
        }
    }
}
