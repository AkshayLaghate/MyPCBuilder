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

        holder.tvProcessorBrand.setText(itemList.get(position).getBrand());
        holder.tvProcessorModel.setText(itemList.get(position).getModel());
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

        TextView tvProcessorBrand, tvProcessorModel;
        Button bSelectProcessor;

        public ProcessorViewHolder(View itemView) {
            super(itemView);

            tvProcessorBrand = (TextView) itemView.findViewById(R.id.tvProcessorItemBrand);
            tvProcessorModel = (TextView) itemView.findViewById(R.id.tvProcessorItemModel);

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
