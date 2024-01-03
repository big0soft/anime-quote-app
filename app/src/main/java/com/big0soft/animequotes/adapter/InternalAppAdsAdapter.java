package com.big0soft.animequotes.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.big0soft.animequotes.R;
import com.big0soft.animequotes.databinding.InternalAdsItemBinding;
import com.big0soft.animequotes.model.InternalAds;
import com.big0soft.resource.adapter.Adapter;
import com.big0soft.resource.adapter.OnClickItem;
import com.big0soft.resource.adapter.ViewHolder;

import java.util.List;
import java.util.Objects;

public class InternalAppAdsAdapter extends Adapter<InternalAds> {
    private final OnClickItem<InternalAds> onClickItem;

    public InternalAppAdsAdapter(List<InternalAds> listItems) {
        super(listItems);
        onClickItem = null;
    }

    public InternalAppAdsAdapter(List<InternalAds> listItems, OnClickItem<InternalAds> onClickItem) {
        super(listItems);
        this.onClickItem = onClickItem;
    }

    @NonNull
    @Override
    public ViewHolder<InternalAds> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ImageSliderViewHolder(DataBindingUtil.inflate(inflater(parent)
                , R.layout.internal_ads_item
                , parent
                , false));
    }


    class ImageSliderViewHolder extends ViewHolder<InternalAds> {
        private final InternalAdsItemBinding adsImageBinding;

        public ImageSliderViewHolder(ViewDataBinding viewDataBinding) {
            super(viewDataBinding);
            adsImageBinding = ((InternalAdsItemBinding) viewDataBinding);
        }

        @Override
        public void bindView(InternalAds model) {
            adsImageBinding.setModel(model);
            adsImageBinding.getRoot().setOnClickListener(v -> {
                if (Objects.isNull(onClickItem)) return;
                onClickItem.clickItem(getAdapterPosition(), model);
            });
        }
    }
}
