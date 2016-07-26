package com.indcoders.mypcbuilder.Utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.indcoders.mypcbuilder.Model.Monitor;
import com.indcoders.mypcbuilder.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Akki on 26/07/16.
 */

public class MonitorAdapter extends RecyclerView.Adapter<MonitorAdapter.MonitorViewHolder> {

    private final LayoutInflater inflator;
    private final List<Monitor> itemList;
    Context c;

    private MonitorAdapter.OnSelectedListener onSelectedListener;

    public MonitorAdapter(Context context, ArrayList<Monitor> list) {
        inflator = LayoutInflater.from(context);
        c = context;
        itemList = list;
    }

    @Override
    public MonitorAdapter.MonitorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflator.inflate(R.layout.monitor_item, parent, false);
        MonitorAdapter.MonitorViewHolder holder = new MonitorAdapter.MonitorViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(MonitorAdapter.MonitorViewHolder holder, int position) {

        holder.tvMonName.setText(itemList.get(position).getName());
        holder.tvMonSize.setText("Size: " + itemList.get(position).getSize());
        holder.tvMonPanel.setText("Panel: " + itemList.get(position).getPanel());
        holder.tvMonResolution.setText("Resolution: " + itemList.get(position).getResolution());
        holder.tvMonResponse.setText("Response:" + itemList.get(position).getResponse());
        holder.tvMonPrice.setText("Rs." + itemList.get(position).getPrice());

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void setOnSelectedListener(MonitorAdapter.OnSelectedListener mListener) {
        onSelectedListener = mListener;
    }

    public interface OnSelectedListener {
        void onSelected(Monitor monitor);
    }

    class MonitorViewHolder extends RecyclerView.ViewHolder {

        TextView tvMonName, tvMonSize, tvMonPanel, tvMonResolution, tvMonResponse, tvMonPrice;
        Button bSelectMon;

        public MonitorViewHolder(View itemView) {
            super(itemView);

            tvMonName = (TextView) itemView.findViewById(R.id.tvMonItemName);
            tvMonSize = (TextView) itemView.findViewById(R.id.tvMonItemSize);
            tvMonPanel = (TextView) itemView.findViewById(R.id.tvMonItemPanel);
            tvMonResolution = (TextView) itemView.findViewById(R.id.tvMonItemResolution);
            tvMonResponse = (TextView) itemView.findViewById(R.id.tvMonItemResponse);
            tvMonPrice = (TextView) itemView.findViewById(R.id.tvMonItemPrice);

            bSelectMon = (Button) itemView.findViewById(R.id.bSelectItemMon);

            bSelectMon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onSelectedListener.onSelected(itemList.get(getAdapterPosition()));
                }
            });
        }
    }
}
