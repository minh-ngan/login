package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class send_otp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_otp);
        final EditText inputMobile = findViewById(R.id.edtSđt);
        Button btnLogin = findViewById(R.id.btnLogin);
        final ProgressBar progressBar = findViewById(R.id.progressbar);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(inputMobile.getText().toString().trim().isEmpty()){
                    Toast.makeText(send_otp.this,"Nhập số điện thoại", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                btnLogin.setVisibility(View.INVISIBLE);

               PhoneAuthProvider.getInstance().verifyPhoneNumber("+84" + inputMobile.getText().toString(),
                       60, TimeUnit.SECONDS,
                       send_otp.this,
                       new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
                           @Override
                           public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                               progressBar.setVisibility(View.GONE);
                               btnLogin.setVisibility(View.VISIBLE);

                           }

                           @Override
                           public void onVerificationFailed(@NonNull FirebaseException e) {
                               progressBar.setVisibility(View.GONE);
                               btnLogin.setVisibility(View.VISIBLE);
                               Toast.makeText(send_otp.this,e.getMessage(),Toast.LENGTH_SHORT).show();

                           }

                           @Override
                           public void onCodeSent(@NonNull String verificationId , @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                               progressBar.setVisibility(View.GONE);
                               btnLogin.setVisibility(View.VISIBLE);
                               Intent intent = new Intent(getApplicationContext(), verifyotp.class);
                               intent.putExtra("mobile", inputMobile.getText().toString());
                               intent.putExtra("verificationId",verificationId);
                               startActivity(intent);
                           }
                       });

            }
        });
    }
}