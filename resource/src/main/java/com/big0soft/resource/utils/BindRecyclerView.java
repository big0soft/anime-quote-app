package com.big0soft.resource.utils;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.big0soft.resource.view.WrapContentGridLayoutManager;
import com.big0soft.resource.view.WrapContentHorizontalManager;
import com.big0soft.resource.view.WrapContentLinearLayoutManager;

public class BindRecyclerView {
    // <variable
//    name="adapter"
//    type="androidx.recyclerview.widget.RecyclerView.Adapter" />
    @BindingAdapter("android:setAdapter")
    public static void setAdapter(RecyclerView recyclerView, RecyclerView.Adapter<?> adapter) {
        setAdapter(recyclerView, adapter, new WrapContentLinearLayoutManager(recyclerView.getContext()));
    }

    @BindingAdapter("android:viewPageAdapter")
    public static void setViewPageAdapter(ViewPager2 viewPager, RecyclerView.Adapter<?> adapter) {
        viewPager.setAdapter(adapter);
    }


    @BindingAdapter("android:setHorizontalAdapter")
    public static void setHorizontalAdapter(RecyclerView recyclerView, RecyclerView.Adapter<?> adapter) {
        setAdapter(recyclerView, adapter, new WrapContentHorizontalManager(recyclerView.getContext()));
    }

    @BindingAdapter(value = {"android:setGridAdapter", "android:gridSpam", "android:gridOrientation"})
    public static void setGridAdapter(RecyclerView recyclerView, RecyclerView.Adapter<?> adapter, int spam, int orientation) {
        setAdapter(recyclerView, adapter, new WrapContentGridLayoutManager(recyclerView.getContext(), spam, orientation, false));
    }

    @BindingAdapter(value = {"android:setGridAdapter", "android:gridSpam"})
    public static void setGridAdapter(RecyclerView recyclerView, RecyclerView.Adapter<?> adapter, int spam) {
        setAdapter(recyclerView, adapter, new WrapContentGridLayoutManager(recyclerView.getContext(), spam, LinearLayoutManager.VERTICAL, false));
    }


    @BindingAdapter(value = {"android:setAdapter", "android:setLayoutManager"})
    public static void setAdapter(RecyclerView recyclerView, RecyclerView.Adapter<?> adapter, RecyclerView.LayoutManager layout) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layout);
        recyclerView.setAdapter(adapter);
    }

}
