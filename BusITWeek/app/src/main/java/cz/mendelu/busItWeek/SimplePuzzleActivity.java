package cz.mendelu.busItWeek;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import com.estimote.sdk.repackaged.retrofit_v1_9_0.retrofit.http.HEAD;

import cz.mendelu.busItWeek.library.SimplePuzzle;
import cz.mendelu.busItWeek.library.Task;

import cz.mendelu.busItWeek.library.StoryLine;

public class SimplePuzzleActivity extends AppCompatActivity {

    // 1. Get widgets references
    private EditText answerTextView;
    private TextView questionView;
    private TextView timerView;

    // 2. Open storyline
    private StoryLine storyLine;

    private SimplePuzzle puzzle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_puzzle);
        // 1. Get widgets references
        answerTextView = (EditText) findViewById(R.id.answer_tv);
        questionView = (TextView) findViewById(R.id.question);

        // 2. Open storyline
        storyLine = StoryLine.open(this, MyDemoStoryLineDBHelper.class);
/*
        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                timerView.setText("seconds remaining: " + millisUntilFinished / 1000);
                //here you can have your logic to set text to edittext
            }

            public void onFinish() {
                timerView.setText("done!");
            }

        }.start();
*/
    }

    @Override
    protected void onResume() {
        super.onResume();
        Task currentTask = storyLine.currentTask();
        currentTask.getPuzzle();
    /*
        OPTIE A
        Puzzle puzzle = currentTask.getPuzzle();

        if (puzzle instanceof SimplePuzzle){
           throw new IllegalStateException("No simple puzzle activity");

        }
        OPTIE B
        */
        puzzle = (SimplePuzzle) currentTask.getPuzzle();
        questionView.setText(puzzle.getQuestion());
    }

    public void checkAnswer(View view) {
        // 1. Get user's answer
        String answer = answerTextView.getText().toString();

        // 2. Get correct answer
        String correctAnswer = puzzle.getAnswer();

        if (answer.equalsIgnoreCase(correctAnswer)) {
            storyLine.currentTask().finish(true);
            finish();
        } else {
            answerTextView.setError(getString(R.string.wrongAnswer));
        }
    }
}
