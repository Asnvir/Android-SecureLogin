package com.example.securelogin.util;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import androidx.core.content.ContextCompat;

import com.example.securelogin.StartModel;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;


public class GpsUtil {
    private final Context context;


    public GpsUtil(Context context) {
        this.context = context;

    }

    public void checkPermission(StartModel.GpsPermissionCallback callback) {
        Dexter.withContext(context)
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        callback.onPermissionStatus(true);

                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                        if (permissionDeniedResponse.isPermanentlyDenied()) {
                            showSettingsDialog(callback);
                        }
                        callback.onPermissionStatus(false);
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                })
                .check();
    }

    public void getCoordinates(StartModel.GpsCoordinatesCallback callback) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            callback.onFailure(new RuntimeException("Location permission not granted"));
            return;
        }

        FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(context);
        fusedLocationClient.getLastLocation().addOnSuccessListener(location -> {
            if (location != null) {
                double[] coordinates = {location.getLatitude(), location.getLongitude()};
                callback.onGPSCoordinatesAvailable(coordinates);
            } else {
                callback.onFailure(new RuntimeException("Location not available"));
            }
        }).addOnFailureListener(callback::onFailure);
    }

    private void showSettingsDialog(StartModel.GpsPermissionCallback callback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Permission Denied")
                .setMessage("GPS permission is required for this feature. Please go to app settings and enable the GPS permission.")
                .setPositiveButton("Settings", (dialog, which) -> {
                    openAppSettings();
                })
                .setNegativeButton("Cancel", (dialog, which) -> {
                    callback.onPermissionStatus(false);
                    dialog.dismiss();
                })
                .show();
    }

    private void openAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.fromParts("package", context.getPackageName(), null));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}
