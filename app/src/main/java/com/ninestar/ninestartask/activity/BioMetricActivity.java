package com.ninestar.ninestartask.activity;

import androidx.annotation.NonNull;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.ninestar.ninestartask.R;

import java.util.concurrent.Executor;

public class BioMetricActivity extends BaseActivity {

    private Button mAuthButton;
    private Executor mExecutor;
    private androidx.biometric.BiometricPrompt mBiometricPrompt;
    private BiometricPrompt.PromptInfo mPromptInfo;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_biometric;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuthButton = findViewById(R.id.authentication_button);
        mExecutor = ContextCompat.getMainExecutor(this);
        /*Biometric authentication listener using authentication prompt to get
        * fingerprint authentication result */
        mBiometricPrompt = new BiometricPrompt(BioMetricActivity.this, mExecutor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull @org.jetbrains.annotations.NotNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Toast.makeText(BioMetricActivity.this, "Authentication error : " + errString, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull @org.jetbrains.annotations.NotNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast.makeText(BioMetricActivity.this, "Authentication success", Toast.LENGTH_LONG).show();
                startActivity(new Intent(BioMetricActivity.this, HomeActivity.class));
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(BioMetricActivity.this, "Authentication failed !!! : ", Toast.LENGTH_LONG).show();
            }
        });

        /* Creating biometric fingerprint authentication prompt */
        mPromptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Biometric Authentication")
                .setSubtitle("Login using fingerprint authentication")
                .setNegativeButtonText("Cancel")
                .build();

        mAuthButton.setOnClickListener(v -> {
            mBiometricPrompt.authenticate(mPromptInfo);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mBiometricPrompt.authenticate(mPromptInfo);
    }

}