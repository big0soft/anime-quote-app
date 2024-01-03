package com.big0soft.animequotes.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.big0soft.animequotes.model.QuoteModel;
import com.big0soft.animequotes.util.FirebaseUtil;
import com.google.firebase.database.DataSnapshot;

public class QuoteViewModel extends ViewModel {
    public MutableLiveData<QuoteModel> quoteViewModelMutableLiveData =new MutableLiveData<>();
    public void getQuoteModel(){
        FirebaseUtil.getQuotesRef()
                .get()
                .addOnSuccessListener(dataSnapshot -> {
                    for (DataSnapshot data: dataSnapshot.getChildren()){
                        QuoteModel quoteModel =data.getValue(QuoteModel.class);
                        quoteViewModelMutableLiveData.setValue(quoteModel);
                    }
                });
    }
}
