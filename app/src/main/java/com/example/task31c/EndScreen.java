package com.example.task31c;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class EndScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen);

        TextView congratsTextView = findViewById(R.id.congratsTextView);
        TextView scoreTextView = findViewById(R.id.scoreTextView);
        Intent intent = getIntent();

        congratsTextView.setText("Congratulations " + intent.getStringExtra("username") + "!");
        scoreTextView.setText(intent.getStringExtra("tally") + "/5");
    }

    public void endApp(View view) {
        moveTaskToBack(true);
        System.exit(1);
    }

    public void restartQuiz(View view) {
        Intent intent = getIntent();
        Intent intentNew = new Intent(this, MainActivity.class);

        intentNew.putExtra("questionNum", "1");
        intentNew.putExtra("tally", "0");
        intentNew.putExtra("username", intent.getStringExtra("username"));
        startActivity(intentNew);
    }
}