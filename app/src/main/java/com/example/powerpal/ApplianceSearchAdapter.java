package com.example.powerpal;
// https://gist.github.com/codinginflow/ea0d9aeb791fb2eac190befcec448909


import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;



public class ApplianceSearchAdapter extends RecyclerView.Adapter<ApplianceSearchAdapter.ViewHolder> {
    private static ArrayList<ApplianceItem> applianceList;
    public static ListItemClickListener mOnClickListener;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView applianceName;
        public TextView applianceSpecs;


        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            applianceName = itemView.findViewById(R.id.applianceName);
            applianceSpecs = itemView.findViewById(R.id.applianceSpecs);

        }

        @Override
        public void onClick(View view) {
            runOnUiThread(new Runnable() {
                public void run() {

                }
            });
        }

        private void runOnUiThread(Runnable runnable) {
            int position = getAdapterPosition();
            String[] specs = applianceList.get(position).getApplianceSpecs().split(" ", 0);
            if (specs[5].equals("N/A"))
                specs[5] = String.valueOf(0);
            mOnClickListener.onListItemClick(position,applianceList.get(position).getApplianceName(),Float.parseFloat(specs[1]), Float.parseFloat(specs[3]), Float.parseFloat(specs[5]));
        }
    }

    public ApplianceSearchAdapter(ArrayList<ApplianceItem> _applianceList, ListItemClickListener onClickListener) {
        applianceList = _applianceList;
        this.mOnClickListener = onClickListener;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.appliance_item,
                parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ApplianceItem currentItem = applianceList.get(position);
        holder.applianceName.setText(currentItem.getApplianceName());
        holder.applianceSpecs.setText(currentItem.getApplianceSpecs());
    }

    @Override
    public int getItemCount() {
        return applianceList.size();
    }

    public void filterList(ArrayList<ApplianceItem> filteredList) {
        applianceList = filteredList;
        notifyDataSetChanged();
    }

    interface ListItemClickListener{
        void onListItemClick(int position1, String s, float parseInt, float i, float position);
    }

}