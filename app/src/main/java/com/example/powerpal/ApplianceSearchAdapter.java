package com.example.powerpal;
// https://gist.github.com/codinginflow/ea0d9aeb791fb2eac190befcec448909


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;



public class ApplianceSearchAdapter extends RecyclerView.Adapter<ApplianceSearchAdapter.ViewHolder> {
    private ArrayList<ApplianceItem> applianceList;

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
                Toast.makeText(view.getContext(), "position = ", Toast.LENGTH_SHORT).show();
        }
    }

    public ApplianceSearchAdapter(ArrayList<ApplianceItem> _applianceList) {
        applianceList = _applianceList;
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


}