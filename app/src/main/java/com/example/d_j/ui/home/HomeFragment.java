package com.example.d_j.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.example.d_j.R;
import com.example.d_j.SignUPActivity;

import java.util.HashMap;

public class HomeFragment extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
    SliderLayout sliderLayout;
    HashMap<String, Integer> HashMapForLocal;

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

       // **********************slider code*************

        AddImageUrlLocal();
        sliderLayout = root.findViewById(R.id.slider);

        for (String name : HashMapForLocal.keySet()) {
            TextSliderView textSliderView = new TextSliderView(getContext());

            textSliderView.description(name).image(HashMapForLocal.get(name)).setScaleType(BaseSliderView.ScaleType.CenterCrop).setOnSliderClickListener(this);

            textSliderView.bundle(new Bundle());
            textSliderView.getBundle().putString("extra", name);
            sliderLayout.addSlider(textSliderView);
        }
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.DepthPage);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.setDuration(3000);
        sliderLayout.addOnPageChangeListener(HomeFragment.this);


        CategoryList[] myListData= new CategoryList[]{
                new CategoryList("For Birthday",R.drawable.ic_cake_black_24dp),
                new CategoryList("For Wedding",R.drawable.forwedding),
                new CategoryList("For Party",R.drawable.forparty),
                new CategoryList("For Other",R.drawable.ic_playlist_add_black_24dp),

        };

        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.cat_recycler_view);
        CategoryListAdapter adapter = new CategoryListAdapter(myListData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        recyclerView.setAdapter(adapter);


        return root;
    }

    private void AddImageUrlLocal() {
        HashMapForLocal = new HashMap<String, Integer>();

        HashMapForLocal.put("DJ", R.drawable.slider1);
        HashMapForLocal.put("BEST DJ", R.drawable.slider2);
        HashMapForLocal.put("BAJAO BEST DJ", R.drawable.slider3);
        HashMapForLocal.put("MY DJ ", R.drawable.splashscreen);

    }
    @Override
    public void onStop() {
        sliderLayout.stopAutoCycle();
        super.onStop();
    }


    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Log.d("Slider Demo", "Page Change:" + position);




    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    //***********************slider End*********************
}