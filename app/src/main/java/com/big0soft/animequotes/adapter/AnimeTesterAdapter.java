package com.big0soft.animequotes.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.L;
import com.big0soft.animequotes.R;
import com.big0soft.animequotes.databinding.AnimeItemRvBinding;
import com.big0soft.animequotes.model.QuoteModel;

import java.util.List;

public class AnimeTesterAdapter extends RecyclerView.Adapter<AnimeTesterAdapter.AnimeTesterViewHolder>{
    private List<QuoteModel> animes ;
    private LayoutInflater layoutInflater ;

    public AnimeTesterAdapter(List<QuoteModel> animes) {
        this.animes = animes;
    }

    @NonNull
    @Override
    public AnimeTesterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null){
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        return new AnimeTesterViewHolder(DataBindingUtil.inflate(layoutInflater, R.layout.anime_item_rv,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AnimeTesterViewHolder holder, int position) {
    holder.bindAnimeModel(animes.get(position));
    }

    @Override
    public int getItemCount() {
        return animes.size();
    }

    public class AnimeTesterViewHolder extends RecyclerView.ViewHolder {
        private AnimeItemRvBinding animeItemRvBinding;

        public AnimeTesterViewHolder(@NonNull AnimeItemRvBinding itemView) {
            super(itemView.getRoot());
            this.animeItemRvBinding= itemView ;
        }
        public void bindAnimeModel(QuoteModel animeModel){
            this.animeItemRvBinding.setAnime(animeModel);
        }
    }
}
