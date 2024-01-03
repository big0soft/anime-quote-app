package com.big0soft.animequotes.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.big0soft.animequotes.R;
import com.big0soft.animequotes.databinding.QuotesViewHolderBinding;
import com.big0soft.animequotes.model.QuoteModel;
import com.big0soft.animequotes.util.FirebaseUtil;

import java.util.List;

public class QuoteAdapter extends RecyclerView.Adapter<QuoteAdapter.QuoteViewHolder>{
    private List<QuoteModel>quotes ;
    private LayoutInflater layoutInflater ;

    public QuoteAdapter(List<QuoteModel> quotes) {
        this.quotes = quotes;
    }

    @NonNull
    @Override
    public QuoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null ){
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        return new QuoteViewHolder(DataBindingUtil.inflate(layoutInflater, R.layout.quotes_view_holder,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull QuoteViewHolder holder, int position) {
        holder.setQuotModel(quotes.get(position));
        FirebaseUtil.getImageUrl(quotes.get(position).getCharacter())
                .getDownloadUrl()
                .addOnSuccessListener(uri -> {
                    holder.quotViewHolderBinding.imageView.setImageURI(uri);

                });


    }

    @Override
    public int getItemCount() {
        return quotes.size();
    }

    public class QuoteViewHolder extends RecyclerView.ViewHolder {
        private QuotesViewHolderBinding quotViewHolderBinding ;
        public QuoteViewHolder(@NonNull QuotesViewHolderBinding itemView) {
            super(itemView.getRoot());
            this.quotViewHolderBinding = itemView ;
        }
        public void setQuotModel(QuoteModel quotModel){
            this.quotViewHolderBinding.setQuote(quotModel);
            this.quotViewHolderBinding.executePendingBindings();

        }
    }
}
