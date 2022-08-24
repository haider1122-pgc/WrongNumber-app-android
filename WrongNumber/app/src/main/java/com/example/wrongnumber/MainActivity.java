package com.example.wrongnumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        //code for hold screen for 1000 milli sec
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                openNewAvtivityOnBTNClick();
            }
        },2000);









    }
    private void openNewAvtivityOnBTNClick() {
        
        Intent intent = new Intent(this, StartedScreen.class);
        // intent.putExtra("message",te); //to access the widgets of second activity
        startActivity(intent);



    }

}