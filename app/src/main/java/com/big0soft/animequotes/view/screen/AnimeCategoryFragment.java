package com.big0soft.animequotes.view.screen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;

import com.big0soft.animequotes.R;
import com.big0soft.animequotes.adapter.AnimeAdapter;
import com.big0soft.animequotes.databinding.FragmentCategoryBinding;
import com.big0soft.animequotes.viewmodel.AnimeCategoryViewModel;
import com.big0soft.animequotes.viewmodel.AnimeCategoryViewModel.AnimeCategoryViewModelFactory;
import com.big0soft.resource.model.UIError;
import com.big0soft.resource.screen.BaseFragment;
import com.big0soft.resource.viewmodel.ViewModelUtils;

import java.util.ArrayList;

public class AnimeCategoryFragment extends BaseFragment {
    private FragmentCategoryBinding binding;

    private AnimeCategoryViewModel animeCategoryViewModel;
    private AnimeAdapter animeAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewModelProvider.Factory factory = new AnimeCategoryViewModelFactory();
        animeCategoryViewModel = ViewModelUtils.instanceViewModel(requireActivity()
                , factory
                , AnimeCategoryViewModel.class);
        animeAdapter = new AnimeAdapter(new ArrayList<>());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_category, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState, NavController controller) {
        super.onViewCreated(view, savedInstanceState, controller);

        binding.setAdapter(animeAdapter);
        binding.setLifecycleOwner(requireActivity());
        animeCategoryViewModel.getAnimeLiveDataError().observe(requireActivity(), dataResponse -> {
            binding.fragmentAnimeCategoryIncludeErrorLayout.setErrorData(new UIError(
                    R.mipmap.ic_launcher_logo_round, "Error", dataResponse.message()
            ));
            binding.swipeRefresh.setRefreshing(false);
        });

        animeCategoryViewModel.getAnimeLiveDataUIError().observe(requireActivity(), uiError -> {
            binding.fragmentAnimeCategoryIncludeErrorLayout.setErrorData(uiError);
            binding.swipeRefresh.setRefreshing(false);
        });
        animeCategoryViewModel.getAnimeLiveDataResult().observe(requireActivity(), animeList -> {
            animeAdapter.cleanItems();
            animeAdapter.addItems(animeList);
            binding.swipeRefresh.setRefreshing(false);
        });

        binding.swipeRefresh.setOnRefreshListener(() -> {
            animeCategoryViewModel.loadAnimeCategory();

        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
