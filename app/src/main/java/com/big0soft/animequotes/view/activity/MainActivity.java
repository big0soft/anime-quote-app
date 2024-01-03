package com.big0soft.animequotes.view.activity;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import com.big0soft.animequotes.R;
import com.big0soft.animequotes.databinding.ActivityMainBinding;
import com.big0soft.animequotes.viewmodel.AnimeCategoryViewModel;
import com.big0soft.animequotes.viewmodel.AnimeCategoryViewModel.AnimeCategoryViewModelFactory;
import com.big0soft.animequotes.viewmodel.HomeViewModel;
import com.big0soft.resource.screen.ActivityNavigationCompat;
import com.big0soft.resource.viewmodel.ViewModelUtils;

public class MainActivity extends ActivityNavigationCompat {
    private HomeViewModel homeViewModel;
    private AnimeCategoryViewModel animeCategoryViewModel;
    private NavController controller;
    private NavHostFragment navHostFragment;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        initNavigation();
        initViewModels();


    }

    private void initNavigation() {
        navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mainActivityFragmentContainerView);
        controller = navHostFragment.getNavController();

        NavigationUI.setupWithNavController(binding.bottomNavigationView, controller);
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadViewModelData();
    }

    private void initViewModels() {
        ViewModelProvider.Factory factory;
        factory = new AnimeCategoryViewModelFactory();
        animeCategoryViewModel = ViewModelUtils.instanceViewModel(this, factory
                , AnimeCategoryViewModel.class);
//        ViewModelProvider.Factory factory = new HomeViewModel.HomeViewModelFactory(
//                new QuoteRepositoryTest(),
//                new InternalAdsRepositoryTest()
//        );
//        homeViewModel = ViewModelUtils.instanceViewModel(this, factory, HomeViewModel.class);
    }

    private void loadViewModelData() {
//        homeViewModel.getMostPopularQuotes();
//        homeViewModel.getInternalAds();
        animeCategoryViewModel.loadAnimeCategory();
    }

    @Override
    public NavController controller() {
        return controller;
    }

    @Override
    public NavHostFragment navHostFragment() {
        return navHostFragment;
    }
}