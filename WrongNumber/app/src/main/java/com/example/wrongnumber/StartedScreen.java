package com.example.wrongnumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartedScreen extends AppCompatActivity {
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_started_screen);
        btn = (Button)findViewById(R.id.getStartedButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewAvtivityOnBTNClick();

            }
        });


    }
    private void openNewAvtivityOnBTNClick() {

        Intent intent = new Intent(this, AuthenticationScreen.class);
        // intent.putExtra("message",te); //to access the widgets of second activity
        startActivity(intent);



    }
}