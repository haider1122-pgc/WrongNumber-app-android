package com.example.wrongnumber;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class AuthenticationScreen extends AppCompatActivity {
     Button btn;
     EditText number;

     ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication_screen);
        btn = (Button)findViewById(R.id.continueButton);
        number=findViewById(R.id.phone);
       // pd= new ProgressDialog(AuthenticationScreen.this);
        pb=(ProgressBar) findViewById(R.id.progress);

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (!number.getText().toString().isEmpty()) {
                    if (number.getText().toString().length() == 10) {
                       pb.setVisibility(View.VISIBLE);

                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                "+92" + number.getText().toString(),
                                60,
                                TimeUnit.SECONDS,
                                AuthenticationScreen.this,
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
                                        Intent intent = new Intent(getApplicationContext(), CodeVerificationScreen.class);
                                        // intent.putExtra("message",te); //to access the widgets of second activity
                                        intent.putExtra("backendOTP",otp);
                                        intent.putExtra("phone",number.getText().toString());
                                        pb.setVisibility(View.INVISIBLE);
                                        startActivity(intent);
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
    private void openNewAvtivityOnBTNClick() {

        Intent intent = new Intent(this, CodeVerificationScreen.class);
        // intent.putExtra("message",te); //to access the widgets of second activity
        startActivity(intent);



    }
}