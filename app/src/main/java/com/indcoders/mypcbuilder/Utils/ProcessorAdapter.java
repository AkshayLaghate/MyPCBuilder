package com.indcoders.mypcbuilder.Utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.indcoders.mypcbuilder.Model.Processor;
import com.indcoders.mypcbuilder.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Akki on 20/07/16.
 */

public class ProcessorAdapter extends RecyclerView.Adapter<ProcessorAdapter.ProcessorViewHolder> {

    private final LayoutInflater inflator;
    private final List<Processor> itemList;
    Context c;

    private OnSelectedListener onSelectedListener;

    public ProcessorAdapter(Context context, ArrayList<Processor> list) {
        inflator = LayoutInflater.from(context);
        c = context;
        itemList = list;
    }

    @Override
    public ProcessorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflator.inflate(R.layout.processor_item, parent, false);
        ProcessorViewHolder holder = new ProcessorViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(ProcessorViewHolder holder, int position) {

        holder.tvProcessorName.setText(itemList.get(position).getBrand() + " " + itemList.get(position).getModel());
        holder.tvProcessorCore.setText("Cores: " + itemList.get(position).getCore());
        holder.tvProcessorSocket.setText("Socket: " + itemList.get(position).getSocket());
        holder.tvProcessorSpeed.setText("Speed: " + itemList.get(position).getSpeed());
        holder.tvProcessorPrice.setText("Rs." + itemList.get(position).getPrice());

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void setOnSelectedListener(OnSelectedListener mListener) {
        onSelectedListener = mListener;
    }

    public interface OnSelectedListener {
        void onSelected(Processor processor);
    }

    class ProcessorViewHolder extends RecyclerView.ViewHolder {

        TextView tvProcessorName, tvProcessorCore, tvProcessorSpeed, tvProcessorSocket, tvProcessorPrice;
        Button bSelectProcessor;

        public ProcessorViewHolder(View itemView) {
            super(itemView);

            tvProcessorName = (TextView) itemView.findViewById(R.id.tvProcessorItemName);
            tvProcessorCore = (TextView) itemView.findViewById(R.id.tvProcessorItemCore);
            tvProcessorSpeed = (TextView) itemView.findViewById(R.id.tvProcessorItemSpeed);
            tvProcessorSocket = (TextView) itemView.findViewById(R.id.tvProcessorItemSocket);
            tvProcessorPrice = (TextView) itemView.findViewById(R.id.tvProcessorItemPrice);

            bSelectProcessor = (Button) itemView.findViewById(R.id.bSelectItemProcessor);

            bSelectProcessor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onSelectedListener.onSelected(itemList.get(getAdapterPosition()));
                }
            });
        }
    }
}
