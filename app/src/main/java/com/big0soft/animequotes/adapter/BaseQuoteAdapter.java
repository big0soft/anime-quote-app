package com.big0soft.animequotes.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;

import com.big0soft.animequotes.model.Quote;
import com.big0soft.resource.adapter.Adapter;
import com.big0soft.resource.adapter.ViewHolder;

import java.util.List;

public class BaseQuoteAdapter extends Adapter<Quote> {
    public static final int TYPE_TOP_HOME_PAGE_VIEW = 1; //TOP_HOME_PAGE_VIEW

    public BaseQuoteAdapter(List<Quote> listItems) {
        super(listItems);
    }



    @NonNull
    @Override
    public ViewHolder<Quote> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return null;
    }

    class TopHomeViewHolder extends ViewHolder<Quote> {

        public TopHomeViewHolder(ViewDataBinding viewDataBinding) {
            super(viewDataBinding);
        }

        @Override
        public void bindView(Quote model) {

        }
    }


}
