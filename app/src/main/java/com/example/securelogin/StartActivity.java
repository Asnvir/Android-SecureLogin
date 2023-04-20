package com.example.securelogin;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;


import com.example.securelogin.databinding.ActivityStartBinding;
import com.example.securelogin.util.BatteryUtil;
import com.example.securelogin.util.GpsUtil;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class StartActivity extends AppCompatActivity {

    private ActivityStartBinding binding;
    private StartViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        GpsUtil mGpsUtil = new GpsUtil(this);
        BatteryUtil mBatteryUtil = new BatteryUtil(this);
        viewModel = new ViewModelProvider(this, new StartViewModelFactory(mGpsUtil,mBatteryUtil))
                .get(StartViewModel.class);



        registerButtonActions();
        observeCHECKBoxes();


    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    private void registerButtonActions() {
        binding.loginCHCKBXGps.setOnCheckedChangeListener((compoundButton, isChecked) -> viewModel.setIsLocationSelected(isChecked));
        binding.loginCHCKBXBattery.setOnCheckedChangeListener(((compoundButton, isChecked) -> viewModel.setIsBatterySelected(isChecked)));
        binding.loginCHCKBXTime.setOnCheckedChangeListener((compoundButton, isChecked) -> viewModel.setIsTimeSelected(isChecked));
        binding.loginBTNLogin.setOnClickListener(view -> viewModel.login());
    }

    private void observeCHECKBoxes() {
        viewModel.getIsGpsSelectedLiveData().observe(this, isChecked -> binding.loginCHCKBXGps.setChecked(isChecked));
        viewModel.getIsBatterySelectedLiveData().observe(this, isChecked -> binding.loginCHCKBXBattery.setChecked(isChecked));
        viewModel.getIsTimeSelectedLiveData().observe(this, isChecked -> binding.loginCHCKBXTime.setChecked(isChecked));
        viewModel.getIsLoginLiveData().observe(this, isLogin -> {
            if (isLogin) {
                Toast.makeText(this, "SUCCESS", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "FAILED", Toast.LENGTH_SHORT).show();
            }
        });
    }





    // TODO: 18/04/2023
//    private String getCurrentTime() {
//        Calendar calendar = Calendar.getInstance();
//        Date currentTime = calendar.getTime();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//        return simpleDateFormat.format(currentTime);
//    }

}