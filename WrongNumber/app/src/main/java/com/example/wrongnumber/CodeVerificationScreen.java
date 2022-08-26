package com.example.wrongnumber;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class CodeVerificationScreen extends AppCompatActivity {
    EditText otp1,otp2,otp3,otp4,otp5,otp6;
    ImageView img;
    String getOtpBackend,number;
    TextView tv;
    ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_verification_screen);
        img=(ImageView)findViewById(R.id.moveOn);
        getOtpBackend=getIntent().getStringExtra("backendOTP");
        number = getIntent().getStringExtra("phone");
        pb=(ProgressBar) findViewById(R.id.progress);
        tv = (TextView)findViewById(R.id.resend);

        otp1=(EditText)findViewById(R.id.otp1);
        otp2=(EditText)findViewById(R.id.otp2);
        otp3=(EditText)findViewById(R.id.otp3);
        otp4=(EditText)findViewById(R.id.otp4);
        otp5=(EditText)findViewById(R.id.otp5);
        otp6=(EditText)findViewById(R.id.otp6);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input;
                if(!otp1.getText().toString().trim().isEmpty() && !otp2.getText().toString().trim().isEmpty() && !otp3.getText().toString().trim().isEmpty() && !otp4.getText().toString().trim().isEmpty() && !otp5.getText().toString().trim().isEmpty() && !otp6.getText().toString().trim().isEmpty() ){
                    input=otp1.getText().toString()+otp2.getText().toString()+otp3.getText().toString()+otp4.getText().toString()+otp5.getText().toString()+otp6.getText().toString();
                    if(!input.isEmpty()) {
                        pb.setVisibility(View.VISIBLE);
                        PhoneAuthCredential phoneAuthCredential= PhoneAuthProvider.getCredential(
                                getOtpBackend,input
                        );

                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                if (task.isSuccessful()) {
                                                    Intent intent = new Intent(getApplicationContext(), FormScreen.class);
                                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                    Toast.makeText(getApplicationContext(), "verified", Toast.LENGTH_LONG).show();
                                                    startActivity(intent);
                                                    pb.setVisibility(View.INVISIBLE);
                                                } else {
                                                    pb.setVisibility(View.INVISIBLE);
                                                    Toast.makeText(getApplicationContext(), "invalid otp", Toast.LENGTH_LONG).show();
                                                }

                                            }
                                        });

                    }
                    else
                    {
                        pb.setVisibility(View.INVISIBLE);
                        Toast.makeText(getApplicationContext(), "invalid OTP", Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    pb.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(), "please enter all numbers", Toast.LENGTH_LONG).show();
                }
            }
        });
        numberOtpMove();


        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    if (!number.isEmpty()) {
                        if (number.length() == 10) {
                            pb.setVisibility(View.VISIBLE);

                            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                    "+92" + number,
                                    60,
                                    TimeUnit.SECONDS,
                                    CodeVerificationScreen.this,
                                    new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                        @Override
                                        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                            Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_LONG).show();

                                        }

                                        @Override
                                        public void onVerificationFailed(@NonNull FirebaseException e) {

                                            Toast.makeText(getApplicationContext(), "failure otp not sent", Toast.LENGTH_LONG).show();
                                            pb.setVisibility(View.INVISIBLE);
                                        }

                                        @Override
                                        public void onCodeSent(@NonNull String otp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                            super.onCodeSent(otp, forceResendingToken);
                                            pb.setVisibility(View.INVISIBLE);
                                            Toast.makeText(getApplicationContext(), "otp sent", Toast.LENGTH_LONG).show();


                                        }
                                    }

                            );


                        } else {
                            pb.setVisibility(View.INVISIBLE);
                            Toast.makeText(getApplicationContext(), "invalid phone number", Toast.LENGTH_LONG).show();
                        }

                    } else {
                        pb.setVisibility(View.INVISIBLE);
                        Toast.makeText(getApplicationContext(), "please enter a phone number", Toast.LENGTH_LONG).show();
                    }

                }

        });


    }

    private void numberOtpMove() {
        otp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()){
                    otp2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        otp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()){
                    otp3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        otp3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()){
                    otp4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        otp4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()){
                    otp5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        otp5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()){
                    otp6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

}