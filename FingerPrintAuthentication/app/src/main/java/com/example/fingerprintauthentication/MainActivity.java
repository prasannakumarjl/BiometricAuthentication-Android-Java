package com.example.fingerprintauthentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.app.KeyguardManager;
import android.content.Context;
import android.hardware.biometrics.BiometricManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

// The commented logic implements the BiometricMAnager from android core go down to chek the working Biometric manager from Androidx Library



//    Button but;
//    private Executor executor;
//    private BiometricPrompt biometricPrompt;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        but=findViewById(R.id.auth);
//        executor = ContextCompat.getMainExecutor(this);
//        but.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.w("onclick","execute the fingerprint");
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                    FingerprintManager fingerprintManager = (FingerprintManager) MainActivity.this.getSystemService(Context.FINGERPRINT_SERVICE);
//                    if (!fingerprintManager.isHardwareDetected()) {
//                        toast("Your Device Dosen't support fingerprint sensor");
//                    } else if (!fingerprintManager.hasEnrolledFingerprints()) {
//                        toast("Fingerprint setup was not found");
//                    } else {
//                        Executor exe = Executors.newSingleThreadExecutor();
////                        toast("Your Device support fingerprint sensor");
//
//                        BiometricPrompt.AuthenticationCallback callback = new BiometricPrompt.AuthenticationCallback() {
//                            @Override
//                            public void onAuthenticationError(int errorCode, CharSequence errString) {
//                                super.onAuthenticationError(errorCode, errString);
//                            }
//
//                            @Override
//                            public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
//                                super.onAuthenticationSucceeded(result);
//                            }
//
//                            @Override
//                            public void onAuthenticationFailed() {
//                                super.onAuthenticationFailed();
//                            }
//                        }
//                        BiometricPrompt biometricPrompt1 = new BiometricPrompt(this,exe,BiometricPrompt.AuthenticationCallback(){
//
//                        })
//
////                        KeyguardManager km = (KeyguardManager) MainActivity.this.getSystemService(KEYGUARD_SERVICE);
////                        BiometricPrompt.Builder builder = null;
////                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
////                            builder = new BiometricPrompt.Builder(getApplicationContext());
////                        }
////                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
////                            builder.setConfirmationRequired(true);
////                            if (km != null && km.isDeviceSecure()) {
////                                builder.setDeviceCredentialAllowed(true);
////                            } else {
////                                builder.setNegativeButton("Cancel", executor, (dialog, which) -> {
////                                    //something
////                                    toast("Authentication Cancelled");
////                                });
////                            }
////                            builder.setDescription("Use your device Fingerprint to authorize the user");
////                            builder.setTitle("Fingerprint Authentication");
////                            biometricPrompt=builder.build();
////                            biometricPrompt.authenticate(new CancellationSignal(), executor, new BiometricPrompt.AuthenticationCallback() {
////                                @Override
////                                public void onAuthenticationError(int errorCode, CharSequence errString) {
////                                    super.onAuthenticationError(errorCode, errString);
////                                    toast("Authentication Error: "+errString);
////                                }
////                                @Override
////                                public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
////                                    super.onAuthenticationSucceeded(result);
////                                    toast("Authentication Verified...!");
////                                }
////                                @Override
////                                public void onAuthenticationFailed() {
////                                    super.onAuthenticationFailed();
////                                    toast("Authentication Failed!");
////                                }
////                            });
////                        }else{
////                            toast("Api less than q");
////
////                        }
//
//
//
//                    }
//                }
//            }
//        });
//    }
//    public void toast(String message){
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
//    }

    private Executor executor;
    private androidx.biometric.BiometricPrompt biometricPrompt;
    private androidx.biometric.BiometricPrompt.PromptInfo promptInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button biometricLoginButton = findViewById(R.id.auth);
        biometricLoginButton.setOnClickListener(view -> {
            biometricPrompt.authenticate(promptInfo);
        });
        executor = ContextCompat.getMainExecutor(this);
        biometricPrompt = new androidx.biometric.BiometricPrompt(MainActivity.this,
                executor, new androidx.biometric.BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull @org.jetbrains.annotations.NotNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                toast("Authentication Error: "+errString);
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull @org.jetbrains.annotations.NotNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                toast("Authentication Verified...!");
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                toast("Authentication Failed!");
            }
        });

        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Security Authentication")
                .setSubtitle("You can Login using your device credentials")
                .setNegativeButtonText("Cancel")
                .build();


    }
    public void toast(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

}