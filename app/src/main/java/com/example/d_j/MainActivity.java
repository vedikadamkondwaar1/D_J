package com.example.d_j;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
    SliderLayout sliderLayout;
    HashMap<String, Integer> HashMapForLocal;
    Button User_Login,Dj_Login,Login_bt,Sign_Up;
    EditText email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        User_Login = findViewById(R.id.userlogin);
        Dj_Login = findViewById(R.id.djlogin);
        Login_bt = findViewById(R.id.login);
        Sign_Up = findViewById(R.id.signup);
        Sign_Up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SignUPActivity.class);
                startActivity(intent);
            }
        });




        //=========================================Slider=================================================================================
        AddImageUrlLocal();
        sliderLayout = findViewById(R.id.slider);

        for (String name : HashMapForLocal.keySet()) {
            TextSliderView textSliderView = new TextSliderView(MainActivity.this);

            textSliderView.description(name).image(HashMapForLocal.get(name)).setScaleType(BaseSliderView.ScaleType.CenterCrop).setOnSliderClickListener(this);

            textSliderView.bundle(new Bundle());
            textSliderView.getBundle().putString("extra", name);
            sliderLayout.addSlider(textSliderView);
        }
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.DepthPage);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.setDuration(3000);
        sliderLayout.addOnPageChangeListener(MainActivity.this);


    }




    private void AddImageUrlLocal() {
        HashMapForLocal = new HashMap<String, Integer>();

        HashMapForLocal.put("DJ", R.drawable.img1);
        HashMapForLocal.put("BEST DJ", R.drawable.img2);
        HashMapForLocal.put("BAJAO BEST DJ", R.drawable.img3);
        HashMapForLocal.put("MY DJ ", R.drawable.img4);

    }
    @Override
    public void onStop() {
        sliderLayout.stopAutoCycle();
        super.onStop();
    }


    @Override
    public void onSliderClick(BaseSliderView slider) {
        Log.e("silder clicked:", slider.getBundle().get("extra") + "");



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
    //===================================================================***slider========================================================
}
