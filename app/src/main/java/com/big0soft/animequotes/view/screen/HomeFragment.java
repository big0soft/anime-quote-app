package com.big0soft.animequotes.view.screen;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.big0soft.animequotes.R;
import com.big0soft.animequotes.adapter.AdsTesterAdapter;
import com.big0soft.animequotes.adapter.AnimeTesterAdapter;
import com.big0soft.animequotes.adapter.InternalAppAdsAdapter;
import com.big0soft.animequotes.adapter.MostPopularAdapter;
import com.big0soft.animequotes.adapter.QuoteAdapter;
import com.big0soft.animequotes.databinding.FragmentHomeBinding;
import com.big0soft.animequotes.model.AdsTester;
import com.big0soft.animequotes.model.InternalAds;
import com.big0soft.animequotes.model.QuoteModel;
import com.big0soft.animequotes.util.FirebaseUtil;
import com.big0soft.animequotes.viewmodel.HomeViewModel;
import com.big0soft.animequotes.viewmodel.QuoteViewModel;
import com.big0soft.resource.adapter.animation.TransformerImpl;
import com.big0soft.resource.helper.TAGs;
import com.big0soft.resource.screen.BaseFragment;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends BaseFragment {

    private FragmentHomeBinding binding;

    private HomeViewModel homeViewModel;

    private MostPopularAdapter mostPopularAdapter;
//    private InternalAppAdsAdapter internalAppAdsAdapter;
    private QuoteAdapter quoteAdapter;
    private List<QuoteModel> quotes;
    private QuoteViewModel quoteViewModel;
    private List<InternalAds> ads;
    private AdsTesterAdapter testerAdapter;
    private List<AdsTester> adsTester ;
    private AnimeTesterAdapter animeTesterAdapter ;
    private List<QuoteModel>animeList ;
    public static final int DEFAULT_CURRENT_ITEM_POSITION = 1;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ViewModelProvider.Factory factory = new HomeViewModel.HomeViewModelFactory(
//                new QuoteRepositoryTest(),
//                new InternalAdsRepositoryTest()
//        );
//        homeViewModel = ViewModelUtils.instanceViewModel(requireActivity(), factory, HomeViewModel.class);
        ads = new ArrayList<>() ;
        adsTester = new ArrayList<>();
        animeList = new ArrayList<>() ;
        mostPopularAdapter = new MostPopularAdapter(new ArrayList<>());
//        internalAppAdsAdapter = new InternalAppAdsAdapter(ads);
        testerAdapter = new AdsTesterAdapter(adsTester) ;
        animeTesterAdapter = new AnimeTesterAdapter(animeList);




    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        quoteViewModel = new ViewModelProvider(this).get(QuoteViewModel.class);
        setupViewPagerAdds();
        setUpQuotesAdapter();
        setUpAnimeList();
        return binding.getRoot();
    }

    private void setUpAnimeList() {
        binding.animeRv.setLayoutManager(new LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false));
        binding.animeRv.setHasFixedSize(true);
        binding.animeRv.setAdapter(animeTesterAdapter);
        setAnimeTesterAdapterLis();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void setAnimeTesterAdapterLis() {
        FirebaseUtil.getQuotesRef()
                .get()
                .addOnSuccessListener(dataSnapshot -> {
                    for (DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){
                        QuoteModel anime = dataSnapshot1.getValue(QuoteModel.class);
                        animeList.add(anime);
                        animeTesterAdapter.notifyDataSetChanged();
                    }

                });
    }

    private void setUpQuotesAdapter() {
        quotes = new ArrayList<>();
        quoteAdapter = new QuoteAdapter(quotes);
        binding.quotesRv.setHasFixedSize(true);
        binding.quotesRv.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.quotesRv.setAdapter(quoteAdapter);
        setQuotesList();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void setQuotesList() {
        quoteViewModel.getQuoteModel();
        quoteViewModel.quoteViewModelMutableLiveData.observe(getViewLifecycleOwner(), quoteModel ->
        {
            quotes.add(quoteModel);
            quoteAdapter.notifyDataSetChanged();
        });
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.setMostPopularAdapter(mostPopularAdapter);
//        binding.setInternalAdsAdapter(internalAppAdsAdapter);
        binding.setLifecycleOwner(this);
//        homeViewModel.getInternalAdsLiveData().observe(requireActivity(), internalAds -> {
//            Log.i(TAGs.TAG(getClass()), "onViewCreated: " + internalAds);
//            if (internalAds.isEmpty()) {
//                binding.fragmentHomeViewPager2Adds.setVisibility(View.GONE);
//                return;
//            }
//            internalAppAdsAdapter.addItems(internalAds);
//            setupSliderIndicators(internalAds.size());
//
//        });
//        homeViewModel.getQuotesLiveData().observe(requireActivity(), quotes -> {
//            Log.i(TAGs.TAG(getClass()), "onViewCreated: " + quotes);
//            mostPopularAdapter.addItems(quotes);
//        });


    }



    private void setupViewPagerAdds() {
        binding.fragmentHomeViewPager2Adds.setOffscreenPageLimit(3);
        binding.fragmentHomeViewPager2Adds.setAdapter(testerAdapter);
        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new TransformerImpl());
        binding.fragmentHomeViewPager2Adds.setPageTransformer(compositePageTransformer);
        binding.fragmentHomeViewPager2Adds.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {super.onPageSelected(position);
//                if (position == (internalAppAdsAdapter.getItemCount() - 1)) {
//                    setCurrentSliderIndicator(0);
//                    binding.fragmentHomeViewPager2Adds.setCurrentItem(0, true);
//                    return;
//                }
                setCurrentSliderIndicator(position);}
        });

        adsTester.add(new AdsTester(R.drawable.l));
        adsTester.add(new AdsTester(R.drawable.light));
        adsTester.add(new AdsTester(R.drawable.luffy));
        adsTester.add(new AdsTester(R.drawable.zoro));
        setupSliderIndicators(adsTester.size());

    }

    private void setupSliderIndicators(int count) {
        Log.d(TAGs.TAG(getClass()), "sliderImage:count " + count);
        ImageView[] indicator = new ImageView[count];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(((int) getResources().getDimension(com.intuit.sdp.R.dimen._7sdp)), ((int) getResources().getDimension(com.intuit.sdp.R.dimen._7sdp)));
        layoutParams.setMargins(8, 0, 8, 0);
        binding.layoutSliderIndicators.removeAllViews();
        for (int i = 0; i < indicator.length; i++) {
            indicator[i] = new ImageView(requireContext());
            indicator[i].setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.background_slider_indicator_inactive));
            indicator[i].setLayoutParams(layoutParams);
            binding.layoutSliderIndicators.addView(indicator[i]);
        }
        setCurrentSliderIndicator(DEFAULT_CURRENT_ITEM_POSITION);
    }

    private void setCurrentSliderIndicator(int position) {
        int childCount = binding.layoutSliderIndicators.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) binding.layoutSliderIndicators.getChildAt(i);
            if (i == position) {
                imageView.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.background_slider_indicator_active));
            } else {
                imageView.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.background_slider_indicator_inactive));
            }
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}