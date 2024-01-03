package com.big0soft.animequotes.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.big0soft.animequotes.R;
import com.big0soft.animequotes.databinding.RowAnimeCategoryBinding;
import com.big0soft.animequotes.model.Anime;
import com.big0soft.resource.adapter.Adapter;
import com.big0soft.resource.adapter.ViewHolder;

import java.util.List;

public class AnimeAdapter extends Adapter<Anime> {
    public AnimeAdapter(List<Anime> listItems) {
        super(listItems);
    }

    @NonNull
    @Override
    public ViewHolder<Anime> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AnimeAdapterViewHolder(DataBindingUtil.inflate(singleInflater(parent), R.layout.row_anime_category
                , parent, false));
    }

    public class AnimeAdapterViewHolder extends ViewHolder<Anime> {
        private RowAnimeCategoryBinding binding;

        public AnimeAdapterViewHolder(ViewDataBinding viewDataBinding) {
            super(viewDataBinding);

            binding = getBinding();
        }


        @Override
        public void bindView(Anime model) {
            binding.setAnime(model);
        }
    }
}
