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
    Button buttonClicked;
    Boolean next = false;

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
                break;
            case "2":
                progressBar.setImageResource(R.drawable.progress2of5);
                title.setText("Referencing Views");
                questionText.setText("What of the three options is a unique identifier for a view?");
                buttonOne.setText("gravity");
                buttonTwo.setText("id");
                buttonThree.setText("text");
                intent.putExtra("currentAnswer", "id");
                break;
            case "3":
                progressBar.setImageResource(R.drawable.progress3of5);
                title.setText("Onscreen Measurements ");
                questionText.setText("What does the unit abbreviation for px stand for?");
                buttonOne.setText("dependent");
                buttonTwo.setText("pixels");
                buttonThree.setText("inches");
                intent.putExtra("currentAnswer", "pixels");
                break;
            case "4":
                progressBar.setImageResource(R.drawable.progress4of5);
                title.setText("Object Naming");
                questionText.setText("What naming convention is used primarily in Andriod Studio for SIT305?");
                buttonOne.setText("camelcase");
                buttonTwo.setText("snakecase");
                buttonThree.setText("flat case");
                intent.putExtra("currentAnswer", "camelcase");
                break;
            case "5":
                progressBar.setImageResource(R.drawable.progress5of5);
                title.setText("Image Referencing");
                questionText.setText("What file directory (folder) are images put in so that they can be used in the activity xml files?");
                buttonOne.setText("manifests");
                buttonTwo.setText("drawable");
                buttonThree.setText("Gradle Scripts");
                intent.putExtra("currentAnswer", "drawable");
                break;
        }
    }

    public void nextQuestion(View view) {
        if (buttonClicked != null && next == true) {
            next(view);
        } else if (buttonClicked != null) {
            checkAnswer();
            next = true;
            switchSubmitButton();
        } else {
            Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show();
        }

    }

    private void switchSubmitButton() {
        Button nextButton = findViewById(R.id.nextButton);
        nextButton.setText("Next");
    }

    public void buttonOne(View view) {
        if (canClick) {
            buttonClicked = findViewById(R.id.optionOneButton);
        }
    }

    public void buttonTwo(View view) {
        if (canClick) {
            buttonClicked = findViewById(R.id.optionTwoButton);
        }
    }

    public void buttonThree(View view) {
        if (canClick) {
            buttonClicked = findViewById(R.id.optionThreeButton);
        }
    }

    public void checkAnswer() {
        Intent intent = getIntent();
        String correctAnswer = intent.getStringExtra("currentAnswer");
        Button optionOne = findViewById(R.id.optionOneButton);
        Button optionTwo = findViewById(R.id.optionTwoButton);
        Button optionThree = findViewById(R.id.optionThreeButton);
        Button correctButton;

        if (correctAnswer.equals(optionOne.getText().toString())) {
            correctButton = optionOne;
        } else if (correctAnswer.equals(optionTwo.getText().toString())) {
            correctButton = optionTwo;
        } else if (correctAnswer.equals(optionThree.getText().toString())) {
            correctButton = optionThree;
        } else {
            correctButton = null;
            Toast.makeText(this, "An error has occurred", Toast.LENGTH_SHORT).show();
        }

        if (correctButton == buttonClicked) {
            Integer temp = Integer.parseInt(intent.getStringExtra("tally"));
            temp += 1;
            intent.putExtra("tally", temp.toString());
            correctButton.setBackgroundColor(Color.GREEN);

        } else {
            buttonClicked.setBackgroundColor(Color.RED);
            correctButton.setBackgroundColor(Color.GREEN);
        }
        canClick = false;
    }

    public void next(View view) {
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
            startActivity(intent2);
        }
    }
}