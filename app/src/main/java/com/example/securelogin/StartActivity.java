package com.example.securelogin;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;


import com.example.securelogin.databinding.ActivityStartBinding;

public class StartActivity extends AppCompatActivity {

    private ActivityStartBinding binding ;
    private StartViewModel viewModel ;
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
        binding.loginCHCKBXLocation.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if (isChecked) {
                handleLocationPermission();
            } else {
                setLocationSelection(false);
            }
        });

        binding.loginCHCKBXBattery.setOnCheckedChangeListener(((compoundButton, isChecked) -> viewModel.setIsBatterySelected(isChecked)));
        binding.loginCHCKBXTime.setOnCheckedChangeListener((compoundButton, isChecked) -> viewModel.setIsTimeSelected(isChecked));
        binding.loginBTNLogin.setOnClickListener(view -> viewModel.login());
    }

    private void observeCHECKBoxes() {
        viewModel.getIsLocationSelectedLiveData().observe(this, isChecked -> {
            binding.loginCHCKBXLocation.setChecked(isChecked);
            Toast.makeText(this, isChecked.toString(), Toast.LENGTH_SHORT).show();
        });
        viewModel.getIsBatterySelectedLiveData().observe(this, isChecked -> binding.loginCHCKBXBattery.setChecked(isChecked));
        viewModel.getIsTimeSelectedLiveData().observe(this, isChecked -> binding.loginCHCKBXTime.setChecked(isChecked));
    }

    
    @Override
    protected void onResume() {
        super.onResume();
        checkLocationPermission();
    }

    
    private void checkLocationPermission() {
        boolean isLocationPermissionGranted = checkPermission();
        setLocationSelection(isLocationPermissionGranted);
    }

    private void handleLocationPermission() {
        if (!checkPermission()) {
            requestLocationPermission();
        } else {
            setLocationCoordinates();
            setLocationSelection(true);
        }
    }

    private void setLocationCoordinates() {
        double[] location = {0.0, 0.0}; // Placeholder latitude and longitude values

        // Get the location manager
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        //// Check if location permission is granted
        if (checkPermission()) {

            // Get the last known location from the location manager
            Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            // Check if last known location is available
            if (lastKnownLocation != null) {
                // Get the latitude and longitude from the last known location
                location[0] = lastKnownLocation.getLatitude(); // Latitude
                location[1] = lastKnownLocation.getLongitude(); // Longitude

                viewModel.setL
            }
        }
        // TODO: 16/04/2023  return location;
    }

    private boolean checkPermission() {
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestLocationPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                GPS_PERMISSION_REQUEST_CODE);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        handlePermissionRequestResult(requestCode, grantResults);
    }

    private void handlePermissionRequestResult(int requestCode, int[] grantResults) {
        if (requestCode == GPS_PERMISSION_REQUEST_CODE) {
            if (isLocationPermissionGranted(grantResults)) {
                handleLocationPermissionGranted();
            } else {
                handleLocationPermissionDenied();
            }
        }
    }

    private boolean isLocationPermissionGranted(int[] grantResults) {
        return grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED;
    }

    private void handleLocationPermissionGranted() {
        // GPS permission is granted, perform the GPS-related operation here
        setLocationSelection(true);
    }

    private void handleLocationPermissionDenied() {
        if (!shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
            // Permission denied and "Don't ask again" option selected, show a dialog to prompt user to open app settings
            showOpenSettingsDialog();
        } else {
            // Permission denied, but "Don't ask again" option not selected, show a rationale message or take appropriate action
            setLocationSelection(false);
            Toast.makeText(this, "GPS permission denied", Toast.LENGTH_SHORT).show();
        }
    }

    private void showOpenSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Permission Denied")
                .setMessage("GPS permission is required for this app. Please open app settings and enable the GPS permission.")
                .setPositiveButton("Open Settings", (dialog, which) -> {
                    // Open app settings
                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri = Uri.fromParts("package", getPackageName(), null);
                    intent.setData(uri);
                    startActivity(intent);
                })
                .setNegativeButton("Cancel", (dialog, which) -> {
                    setLocationSelection(false);
                    dialog.dismiss();
                })
                .setCancelable(false)
                .show();
    }

    private void setLocationSelection(boolean isLocationPermissionGranted) {
        viewModel.setIsLocationSelected(isLocationPermissionGranted);
    }

}