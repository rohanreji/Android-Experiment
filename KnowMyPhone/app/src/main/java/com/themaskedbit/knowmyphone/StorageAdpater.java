package com.themaskedbit.knowmyphone;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class StorageAdpater extends RecyclerView.Adapter<StorageAdpater.StorageViewHolder> {
    int[] imageArray;
    StorageAdpater(int[] imageArray){
        this.imageArray = imageArray;
    }
    @Override
    public StorageAdpater.StorageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.storage_list,parent,false);
        StorageViewHolder vH = new StorageViewHolder(v);
        return vH;
    }

    @Override
    public void onBindViewHolder(StorageAdpater.StorageViewHolder holder, int position) {
        holder.im.setImageResource(imageArray[position]);
    }

    @Override
    public int getItemCount() {
        return imageArray.length;
    }

    public class StorageViewHolder extends RecyclerView.ViewHolder {
        private ImageView im;
        public StorageViewHolder(View itemView) {
            super(itemView);
            im=itemView.findViewById(R.id.storage_list_image);
        }
    }
}
