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
    private static final int GPS_PERMISSION_REQUEST_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot()); // Move setContentView to the beginning

        viewModel = new ViewModelProvider(this).get(StartViewModel.class); // Use ViewModelProvider to create ViewModel
        registerButtonActions();
        observeCHECKBoxes();


    }

    private void registerButtonActions() {
        binding.loginCHCKBXGps.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if (isChecked) {
                Dexter.withContext(this)
                        .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                                // GPS permission granted, perform the GPS-related operation here
                                setGpsSelection(true);
                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                                // GPS permission is denied, handle the case here
                                if (permissionDeniedResponse.isPermanentlyDenied()) {
                                    // Show custom dialog to navigate to app's settings
                                    showSettingsDialog();
                                }
                                setGpsSelection(false);
                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                                // Show a rationale message for the permission
                                permissionToken.continuePermissionRequest();
                            }
                        })
                        .check();
            } else {
                setGpsSelection(false);
            }
        });

        binding.loginCHCKBXBattery.setOnCheckedChangeListener(((compoundButton, isChecked) -> {
            // TODO: 18/04/2023   viewModel.setBatteryPercentage(getBatteryPercentage(this));
            viewModel.setIsBatterySelected(isChecked);
        }));
        binding.loginCHCKBXTime.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            // TODO: 18/04/2023    viewModel.setTime(getCurrentTime());
            viewModel.setIsTimeSelected(isChecked);
        });
        binding.loginBTNLogin.setOnClickListener(view -> {
            getCoordinates();
            viewModel.login();
        });
    }

    private void observeCHECKBoxes() {
        viewModel.getIsGpsSelectedLiveData().observe(this, isChecked -> {
            binding.loginCHCKBXGps.setChecked(isChecked);
            Toast.makeText(this, isChecked.toString(), Toast.LENGTH_SHORT).show();
        });
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

    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Permission Denied")
                .setMessage("GPS permission is required for this feature. Please go to app settings and enable the GPS permission.")
                .setPositiveButton("Settings", (dialog, which) -> {
                    // Open app settings page
                    openAppSettings();
                })
                .setNegativeButton("Cancel", (dialog, which) -> {
                    // Handle cancel button click
                    // Do something here, e.g. show a message or close the dialog
                    setGpsSelection(false);
                    Toast.makeText(this, "Permission Denied. GPS feature will not work.", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                })
                .show();
    }


    private void openAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.fromParts("package", getPackageName(), null));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (checkGpsPermission()) {
            // GPS permission is already granted, perform the GPS-related operation here
            setGpsSelection(true);
        } else {
            setGpsSelection(false);
        }
    }

    private void setGpsSelection(boolean isLocationPermissionGranted) {
        if (isLocationPermissionGranted) viewModel.setLocationCoordinates(getCoordinates());
        viewModel.setIsLocationSelected(isLocationPermissionGranted);
    }

    private boolean checkGpsPermission() {
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    private double[] getCoordinates() {
        double[] location = {0.0, 0.0};
        if (checkGpsPermission()) {
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            Location lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (lastLocation != null) {
                location[0] = lastLocation.getLatitude();
                location[1] = lastLocation.getLongitude();
                Toast.makeText(this, "Latitude: " + location[0] + ", Longitude: " + location[1], Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Failed to get GPS coordinates", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "GPS permission not granted", Toast.LENGTH_SHORT).show();
        }
        return location;
    }


    // TODO: 18/04/2023
//    private String getCurrentTime() {
//        Calendar calendar = Calendar.getInstance();
//        Date currentTime = calendar.getTime();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//        return simpleDateFormat.format(currentTime);
//    }

}