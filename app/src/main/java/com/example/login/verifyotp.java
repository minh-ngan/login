package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class verifyotp extends AppCompatActivity {
    EditText edtInputOTP,edtInputOTP_2,edtInputOTP_3,edtInputOTP_4,edtInputOTP_5,edtInputOTP_6;
    private String verificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifyotp);
        TextView txtMobile = findViewById(R.id.txtMobile);
        txtMobile.setText(String.format("+84-",getIntent().getStringExtra("sdt")));

        edtInputOTP = findViewById(R.id.edtInputOTP);
        edtInputOTP_2 = findViewById(R.id.edtInputOTP_2);
        edtInputOTP_3 = findViewById(R.id.edtInputOTP_3);
        edtInputOTP_4 = findViewById(R.id.edtInputOTP_4);
        edtInputOTP_5 = findViewById(R.id.edtInputOTP_5);
        edtInputOTP_6 = findViewById(R.id.edtInputOTP_6);
        setupInputs();

        final ProgressBar progressBar = findViewById(R.id.progressbar);
        final Button btnVerify = findViewById(R.id.btnVerify);
        verificationId = getIntent().getStringExtra("verificationId");

        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtInputOTP.getText().toString().trim().isEmpty()
                || edtInputOTP_2.getText().toString().trim().isEmpty()
                ||edtInputOTP_3.getText().toString().trim().isEmpty()
                ||edtInputOTP_4.getText().toString().trim().isEmpty()
                || edtInputOTP_5.getText().toString().trim().isEmpty()
                || edtInputOTP_6.getText().toString().trim().isEmpty()){
                    Toast.makeText(verifyotp.this,"Please enter code", Toast.LENGTH_SHORT).show();
                    return;
                }
                String code = edtInputOTP.getText().toString()+
                        edtInputOTP_2.getText().toString()+
                        edtInputOTP_3.getText().toString()+
                        edtInputOTP_4.getText().toString()+
                        edtInputOTP_5.getText().toString()+
                        edtInputOTP_6.getText().toString();
                if(verificationId != null){
                    progressBar.setVisibility(View.VISIBLE);
                    btnVerify.setVisibility(View.INVISIBLE);
                    PhoneAuthCredential phoneAuthProvider = PhoneAuthProvider.getCredential(
                            verificationId,
                            code
                    );
                    FirebaseAuth.getInstance().signInWithCredential(phoneAuthProvider).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressBar.setVisibility(View.GONE);
                            btnVerify.setVisibility(View.VISIBLE);
                            if (task.isSuccessful()){
                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }
                            else  Toast.makeText(verifyotp.this,"Code entered invalid", Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            }
        });
    }
    private void setupInputs(){
        edtInputOTP.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().trim().isEmpty()){
                    edtInputOTP_2.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edtInputOTP_2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().trim().isEmpty()){
                    edtInputOTP_3.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edtInputOTP_3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().trim().isEmpty()){
                    edtInputOTP_4.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edtInputOTP_4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().trim().isEmpty()){
                    edtInputOTP_5.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edtInputOTP_5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().trim().isEmpty()){
                    edtInputOTP_6.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
}