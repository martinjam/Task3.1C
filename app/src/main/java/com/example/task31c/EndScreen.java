package com.example.task31c;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class EndScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen);

        TextView congratsTextView = findViewById(R.id.congratsTextView);
        Intent intent = getIntent();
        congratsTextView.setText("Congratulations " + intent.getStringExtra("username"));
        String tally = intent.getStringExtra("tally");
        TextView scoreTextView = findViewById(R.id.scoreTextView);

        scoreTextView.setText(tally);



    }
}