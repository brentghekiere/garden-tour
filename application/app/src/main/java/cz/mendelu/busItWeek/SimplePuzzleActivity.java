package cz.mendelu.busItWeek;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import cz.mendelu.busItWeek.R;
import cz.mendelu.busItWeek.library.SimplePuzzle;
import cz.mendelu.busItWeek.library.Task;

import cz.mendelu.busItWeek.library.StoryLine;

public class SimplePuzzleActivity extends AppCompatActivity {

    // 1. Get widgets references
    private EditText answerTextView;
    private TextView questionView;

    // 2. Open storyline
    private StoryLine storyLine;

    private SimplePuzzle puzzle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_puzzle2);
        // 1. Get widgets references
        answerTextView = (EditText) findViewById(R.id.answerEditText);
        questionView = (TextView) findViewById(R.id.questionTextView);
        // 2. Open storyline
        storyLine = StoryLine.open(this, MyDemoStoryLineDBHelper.class);
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
            answerTextView.setError(getString(R.string.error_no_bluetooth_enabled));
        }
    }
}