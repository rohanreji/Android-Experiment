package com.themaskedbit.knowmyphone;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Hashtable;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    String[] descriptorDataSet;
    String[] valueDataSet;


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView describeTextView;
        public TextView valueTextView;
        public ViewHolder(View v) {
            super(v);
            describeTextView = (TextView)v.findViewById(R.id.system_description);
            valueTextView = (TextView)v.findViewById(R.id.system_value);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(String[] descriptorDataSet, String[] valueDataSet) {
        this.descriptorDataSet = descriptorDataSet;
        this.valueDataSet = valueDataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.system_list, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.describeTextView.setText(descriptorDataSet[position]);
        holder.valueTextView.setText(valueDataSet[position]);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return descriptorDataSet.length;
    }
}