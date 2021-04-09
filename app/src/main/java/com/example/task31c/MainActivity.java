package com.example.task31c;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
//    public String[] answers = {
//            "ImageView",
//            "id",
//            "px",
//            "camelcase",
//            "drawable"
//    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void startQuiz(View view) {
        EditText username = findViewById(R.id.nameEditText);

        if (username.getText().toString().isEmpty() == false) {
            Intent intent = new Intent(this, QuestionScreen.class);
            intent.putExtra("questionNum", "1");
            intent.putExtra("tally", "0");
            intent.putExtra("username", username.getText().toString());
            startActivity(intent);
        } else {
            Toast.makeText(this, "Please enter a name", Toast.LENGTH_SHORT).show();
        }
    }
}