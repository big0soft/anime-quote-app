package com.big0soft.animequotes.util;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class FirebaseUtil {
    private static final String QUOTES_REF = "quotes";

    public static DatabaseReference getQuotesRef(){
        return FirebaseDatabase.getInstance().getReference(QUOTES_REF);
    }

    public static StorageReference getImageUrl(String character) {
       return FirebaseStorage.getInstance().getReference().child("QuotesImages").child(character);
    }
}
