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
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import java.util.HashMap;

public class SignInActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
    SliderLayout sliderLayout;
    HashMap<String, Integer> HashMapForLocal;
    Button User_Login,Dj_Login,Login_bt,Sign_Up;
    EditText email,password;

    private static final String TAG = "AndroidClarified";
    private GoogleSignInClient googleSignInClient;
    int RC_SIGN_IN=0;

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
                Intent intent = new Intent(SignInActivity.this,SignUPActivity.class);
                startActivity(intent);
            }
        });

        Login_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignInActivity.this,DrawerActicity.class);
                startActivity(i);
            }
        });

        SignInButton signInButton = findViewById(R.id.sign_in_button);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, gso);


        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.sign_in_button:
                        signIn();
                        break;
                }
            }
        });




        //=========================================Slider=================================================================================
        AddImageUrlLocal();
        sliderLayout = findViewById(R.id.slider);

        for (String name : HashMapForLocal.keySet()) {
            TextSliderView textSliderView = new TextSliderView(SignInActivity.this);

            textSliderView.description(name).image(HashMapForLocal.get(name)).setScaleType(BaseSliderView.ScaleType.CenterCrop).setOnSliderClickListener(this);

            textSliderView.bundle(new Bundle());
            textSliderView.getBundle().putString("extra", name);
            sliderLayout.addSlider(textSliderView);
        }
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.DepthPage);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.setDuration(3000);
        sliderLayout.addOnPageChangeListener(SignInActivity.this);


    }

    private void signIn() {

        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            Intent i = new Intent(SignInActivity.this,DrawerActicity.class);
            startActivity(i);

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("error", "signInResult:failed code=" + e.getStatusCode());
        }
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
