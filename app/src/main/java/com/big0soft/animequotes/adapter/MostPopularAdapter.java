package com.big0soft.animequotes.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;

import com.big0soft.animequotes.R;
import com.big0soft.animequotes.databinding.MostPopularItemBinding;
import com.big0soft.animequotes.model.Quote;
import com.big0soft.resource.BindingUtils;
import com.big0soft.resource.adapter.Adapter;
import com.big0soft.resource.adapter.ViewHolder;

import java.util.List;

public class MostPopularAdapter extends Adapter<Quote> {


    public MostPopularAdapter(List<Quote> listItems) {
        super(listItems);
    }

    @NonNull
    @Override
    public ViewHolder<Quote> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MostPopularQuoteViewHolder(BindingUtils.adapterBinding(this, R.layout.most_popular_item, parent));
    }



     class MostPopularQuoteViewHolder extends ViewHolder<Quote> {
        private MostPopularItemBinding binding;

        public MostPopularQuoteViewHolder(ViewDataBinding viewDataBinding) {
            super(viewDataBinding);
            binding = (MostPopularItemBinding) viewDataBinding;
        }

        @Override
        public void bindView(Quote model) {
            binding.setModel(model);
        }
    }
}
