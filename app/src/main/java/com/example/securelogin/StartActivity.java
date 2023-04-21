package com.example.securelogin;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.securelogin.databinding.ActivityStartBinding;
import com.example.securelogin.util.BatteryUtil;
import com.example.securelogin.util.ConditionResult;
import com.example.securelogin.util.GpsUtil;

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
        observeViewModel();
    }

    private void registerButtonActions() {
        binding.loginCHCKBXGps.setOnCheckedChangeListener((compoundButton, isChecked) -> viewModel.setIsLocationSelected(isChecked));
        binding.loginCHCKBXBattery.setOnCheckedChangeListener(((compoundButton, isChecked) -> viewModel.setIsBatterySelected(isChecked)));
        binding.loginCHCKBXTime.setOnCheckedChangeListener((compoundButton, isChecked) -> viewModel.setIsTimeSelected(isChecked));
        binding.loginBTNLogin.setOnClickListener(view -> viewModel.login());
    }

    private void observeViewModel() {
        viewModel.getIsGpsSelectedLiveData().observe(this, isChecked -> binding.loginCHCKBXGps.setChecked(isChecked));
        viewModel.getIsBatterySelectedLiveData().observe(this, isChecked -> binding.loginCHCKBXBattery.setChecked(isChecked));
        viewModel.getIsTimeSelectedLiveData().observe(this, isChecked -> binding.loginCHCKBXTime.setChecked(isChecked));
        viewModel.getErrorMessageLiveData().observe(this,error -> Toast.makeText(this, error, Toast.LENGTH_SHORT).show());
        viewModel.getConditionResultMutableLiveData().observe(this, this::showAlertMessage);
    }

    public void showAlertMessage(ConditionResult conditionResult) {
        String alertMessage = "";

        if (conditionResult.isNoComponentsChecked()) {
            alertMessage += "<br><b>Result: </b>";
            alertMessage += conditionResult.isResult() ? "<b><font color='green'>✓</font></b>" : "<b><font color='red'>✕</font></b>";
            alertMessage += "<br>";
        } else {
            if (conditionResult.isGpsChecked()) {
                alertMessage += "GPS: ";
                alertMessage += conditionResult.isGpsValid() ? "<font color='green'>✓</font>" : "<font color='red'>✕</font>";
                alertMessage += "<br>";
            }
            if (conditionResult.isBatteryChecked()) {
                alertMessage += "Battery: ";
                alertMessage += conditionResult.isBatteryValid() ? "<font color='green'>✓</font>" : "<font color='red'>✕</font>";
                alertMessage += "<br>";
            }
            if (conditionResult.isTimeChecked()) {
                alertMessage += "Time: ";
                alertMessage += conditionResult.isTimeValid() ? "<font color='green'>✓</font>" : "<font color='red'>✕</font>";
                alertMessage += "<br>";
            }
            alertMessage += "<br><b>Result: </b>";
            alertMessage += conditionResult.isResult() ? "<b><font color='green'>✓</font></b>" : "<b><font color='red'>✕</font></b>";
            alertMessage += "<br>";
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Condition Result")
                .setMessage(HtmlCompat.fromHtml(alertMessage, HtmlCompat.FROM_HTML_MODE_LEGACY))
                .setCancelable(false)
                .setPositiveButton("OK", (dialog, which) -> {
                    dialog.dismiss();
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}