package com.big0soft.animequotes.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.big0soft.animequotes.R;
import com.big0soft.animequotes.databinding.InternalAdsItemBinding;
import com.big0soft.animequotes.model.AdsTester;

import java.util.List;

public class AdsTesterAdapter extends RecyclerView.Adapter<AdsTesterAdapter.AdsTesterViewHolder> {
    private List<AdsTester> adsTesterList ;
    private LayoutInflater layoutInflater ;

    public AdsTesterAdapter(List<AdsTester> adsTesterList) {
        this.adsTesterList = adsTesterList;
    }

    @NonNull
    @Override
    public AdsTesterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null){
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        InternalAdsItemBinding internalAdsItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.internal_ads_item,parent,false);
        return new AdsTesterViewHolder(internalAdsItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull AdsTesterViewHolder holder, int position) {
        holder.internalAdsItemBinding.imageView0.setImageResource(adsTesterList.get(position).getImages());
    }

    @Override
    public int getItemCount() {
        return adsTesterList.size();
    }

    public class AdsTesterViewHolder extends RecyclerView.ViewHolder {
        private InternalAdsItemBinding internalAdsItemBinding ;
        public AdsTesterViewHolder(@NonNull InternalAdsItemBinding itemView) {
            super(itemView.getRoot());
            this.internalAdsItemBinding = itemView;
        }

    }


}
