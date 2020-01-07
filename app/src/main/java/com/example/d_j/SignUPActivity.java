package com.example.d_j;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.hbb20.CountryCodePicker;


import java.util.HashMap;

public class SignUPActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener{
    SliderLayout sliderLayout;
    HashMap<String, Integer> HashMapForLocal;
    private EditText editText;
    private CountryCodePicker ccp;

    private static final String KEY_FIRST_NAME = "First_name";
    private static final String KEY_LAST_NAME = "Last_name";
    private static final String KEY_SIGNATURE_NAME = "Signature_name";
    private static final String KEY_LOCATION = "Location";
    private static final String KEY_EMAIL ="Email";
    private static final String KEY_PASSWORD = "Password";
    private static final String KEY_NUMBER = "Mobille_No";
    private static final String KEY_CONFIRM_PASSWORD = "Confirm_Password";
    private static final String KEY_EMPTY = "";

    private EditText etFirst_name;
    private EditText etLast_name;
    private EditText etSignature_name;
    private EditText  etLocation;
    private EditText etEmail;
    private EditText etPassword;
    private EditText etMobile_No;
    private EditText etConfirm_Password;
    private String firstname;
    private String mobile_no;
    private String lastname;
    private String signaturename;
    private String location;
    private String email;
    private  String password;
    private  String confirmpassword;
    Button Sign_Inbt,Sign_Upbt;
    String gender;

    private RadioGroup radioGroup;
    private RadioButton mButton,fButton,oButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        init();


        etFirst_name = findViewById(R.id.firstname);
        etLast_name = findViewById(R.id.lastname);
        etSignature_name = findViewById(R.id.signaturename);
        etLocation = findViewById(R.id.location);
        etEmail = findViewById(R.id.email);
        etPassword = findViewById(R.id.password);
        etMobile_No = findViewById(R.id.mobileno);
       etConfirm_Password = findViewById(R.id.confirmpassword);
        radioGroup = findViewById(R.id.radioGrp);
        mButton = findViewById(R.id.radioM);
        fButton = findViewById(R.id.radioF);
        oButton = findViewById(R.id.radioO);
        Sign_Inbt = findViewById(R.id.sign_in);
        Sign_Upbt = findViewById(R.id.signupbt);
        Sign_Upbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUPActivity.this, SignInActivity.class);
                startActivity(intent);

                int selectedId = radioGroup.getCheckedRadioButtonId();

                if(selectedId == R.id.radioF) {
                    gender = "f";
                }
                else  if(selectedId == R.id.radioM) {
                    gender = "m";
                }
                else if (selectedId == R.id.radioO){
                    gender = "o";
                }


                //Retrieve the data entered in the edit texts
                firstname = etFirst_name.getText().toString().trim();
                lastname = etLast_name.getText().toString().trim();
                signaturename = etSignature_name.getText().toString().trim();
                email = etEmail.getText().toString().trim();
                location = etLocation.getText().toString().trim();
                mobile_no = etMobile_No.getText().toString().trim();
                password = etPassword.getText().toString().trim();
                confirmpassword = etConfirm_Password.getText().toString().trim();


                if (validateInputs()) {

                    Toast.makeText(SignUPActivity.this, "Validated", Toast.LENGTH_SHORT).show();

                }
            }
        });


        //===================================================slider==================================================================
        AddImageUrlLocal();
        sliderLayout = findViewById(R.id.slider);

        for (String name : HashMapForLocal.keySet()) {
            TextSliderView textSliderView = new TextSliderView(SignUPActivity.this);

            textSliderView.description(name).image(HashMapForLocal.get(name)).setScaleType(BaseSliderView.ScaleType.CenterCrop).setOnSliderClickListener(this);

            textSliderView.bundle(new Bundle());
            textSliderView.getBundle().putString("extra", name);
            sliderLayout.addSlider(textSliderView);
        }
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.DepthPage);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.setDuration(3000);
        sliderLayout.addOnPageChangeListener(SignUPActivity.this);


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
    //==========================================================***sliderend============================================================

    private boolean validateInputs() {

        if (KEY_EMPTY.equals(firstname)){
            etFirst_name.setError("First Name Is Required");
            etFirst_name.requestFocus();
            return false;
        }
        if (KEY_EMPTY.equals(lastname)){
            etLast_name.setError("Last Name Is Required");
            etLast_name.requestFocus();
            return false;
        }
        if (KEY_EMPTY.equals(signaturename)){
            etSignature_name.setError("Signature Name Is Required");
            etSignature_name.requestFocus();
            return false;
        }
        if (KEY_EMPTY.equals(email)){
            etEmail.setError("Email Is Required");
            etEmail.requestFocus();
            return false;
        }
        if (KEY_EMPTY.equals(location)){
            etLocation.setError("Location Is Required");
            etLocation.requestFocus();
            return false;
        }
        if (KEY_EMPTY.equals(password)){
            etPassword.setError("Password Is Required");
            etPassword.requestFocus();
            return false;
        }
        if (KEY_EMPTY.equals(confirmpassword)){
            etConfirm_Password.setError("Confirm Password Is Required");
            etConfirm_Password.requestFocus();
            return false;
        }
        if (!password.equals(confirmpassword)){
            etConfirm_Password.setError("Password and Confirm Password does not match");
            etConfirm_Password.requestFocus();
            return false;
        }
        if (KEY_NUMBER.equals(mobile_no)) {
            etMobile_No.setError("Mobile no Is Required");
            etMobile_No.requestFocus();
            return false;
        }

        return true;
    }
    //======

        private void init(){
        ccp = findViewById(R.id.ccp);
        editText = findViewById(R.id.mobileno);
        }



}




