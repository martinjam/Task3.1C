package com.example.task31c;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class QuestionScreen extends AppCompatActivity {
    boolean canClick = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_screen);

        TextView progressTextView = findViewById(R.id.progressTextView);
        TextView welcomeTextView = findViewById(R.id.welcomeTextView);
        Intent intent = getIntent();
        String questionNum = intent.getStringExtra("questionNum");


        progressTextView.setText(questionNum + "/5");

        ImageView progressBar = findViewById(R.id.progressBar);
        TextView title = findViewById(R.id.questionTitleTextView);
        TextView questionText = findViewById(R.id.questionTextView);
        Button buttonOne = findViewById(R.id.optionOneButton);
        Button buttonTwo = findViewById(R.id.optionTwoButton);
        Button buttonThree = findViewById(R.id.optionThreeButton);

        switch (questionNum) {
            case "1":
                progressBar.setImageResource(R.drawable.progress1of5);
                title.setText("Displaying Images");
                questionText.setText("What of the three options is a valid view for displaying an image?");
                buttonOne.setText("ImageView");
                buttonTwo.setText("TextView");
                buttonThree.setText("EditText");
                welcomeTextView.setText("Welcome " + intent.getStringExtra("username"));
                intent.putExtra("currentAnswer", "ImageView");
                intent.putExtra("nextAnswer", "id");
                break;
            case "2":
                progressBar.setImageResource(R.drawable.progress2of5);
                title.setText("Referencing Views");
                questionText.setText("What of the three options is a unique identifier for a view?");
                buttonOne.setText("gravity");
                buttonTwo.setText("id");
                buttonThree.setText("text");
                intent.putExtra("currentAnswer", intent.getStringExtra("nextAnswer"));
                intent.putExtra("nextAnswer", "px");
                break;
            case "3":
                progressBar.setImageResource(R.drawable.progress3of5);
                title.setText("Onscreen Measurements ");
                questionText.setText("What unit abbreviation is used to represent a measurement in pixels?");
                buttonOne.setText("dp");
                buttonTwo.setText("px");
                buttonThree.setText("sp");
                intent.putExtra("currentAnswer", intent.getStringExtra("nextAnswer"));
                intent.putExtra("nextAnswer", "camelcase");
                break;
            case "4":
                progressBar.setImageResource(R.drawable.progress4of5);
                title.setText("Object Naming");
                questionText.setText("What naming convention is used primarily in Andriod Studio for SIT305?");
                buttonOne.setText("camelcase");
                buttonTwo.setText("snakecase");
                buttonThree.setText("flat case");
                intent.putExtra("currentAnswer", intent.getStringExtra("nextAnswer"));
                intent.putExtra("nextAnswer", "drawable");
                break;
            case "5":
                progressBar.setImageResource(R.drawable.progress5of5);
                title.setText("Image Referencing");
                questionText.setText("What file directory (folder) are images put in so that they can be used in the activity xml files?");
                buttonOne.setText("manifests");
                buttonTwo.setText("drawable");
                buttonThree.setText("Gradle Scripts");
                intent.putExtra("currentAnswer", intent.getStringExtra("nextAnswer"));
                break;
            case "6":
                Toast.makeText(this, "end of quiz", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void nextQuestion(View view) {
        Intent intent = getIntent();
        String qNum = intent.getStringExtra("questionNum");

        if (qNum.equals("5")) {
            Intent intentEnd = new Intent(this, EndScreen.class);
            intentEnd.putExtra("username", intent.getStringExtra("username"));
            intentEnd.putExtra("tally", intent.getStringExtra("tally"));
            startActivity(intentEnd);
        } else {
            Intent intent2 = new Intent(this, QuestionScreen.class);
            Integer intNum = Integer.parseInt(qNum) + 1;
            intent2.putExtra("questionNum", intNum.toString());
            intent2.putExtra("username", intent.getStringExtra("username"));
            intent2.putExtra("tally", intent.getStringExtra("tally"));
            intent2.putExtra("currentAnswer", intent.getStringExtra("currentAnswer"));
            intent2.putExtra("nextAnswer", intent.getStringExtra("nextAnswer"));
            startActivity(intent2);
        }
    }

    public void buttonOne(View view) {
        if (canClick) {
            Button clicked = findViewById(R.id.optionOneButton);
            checkAnswer(clicked);
        }
    }

    public void buttonTwo(View view) {
        if (canClick) {
            Button clicked = findViewById(R.id.optionTwoButton);
            checkAnswer(clicked);
        }
    }

    public void buttonThree(View view) {
        if (canClick) {
            Button clicked = findViewById(R.id.optionThreeButton);
            checkAnswer(clicked);
        }
    }

    public void checkAnswer(Button clicked) {
        Intent intent = getIntent();
        String correctAnswer = intent.getStringExtra("currentAnswer");

        if (correctAnswer.equals(clicked.getText().toString())) {
            intent.putExtra("tally", Integer.parseInt(intent.getStringExtra("tally")) + 1);
            clicked.setBackgroundColor(Color.GREEN);
        } else {
            clicked.setBackgroundColor(Color.RED);
        }
        canClick = false;
    }
}