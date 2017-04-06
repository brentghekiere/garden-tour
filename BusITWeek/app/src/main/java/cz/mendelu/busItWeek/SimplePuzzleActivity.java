package cz.mendelu.busItWeek;

<<<<<<< HEAD
=======
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
>>>>>>> f45b0a1535c8d716f43c5e4c379c179a9c8c902f
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

<<<<<<< HEAD
import butterknife.BindView;
import butterknife.ButterKnife;
=======

import com.estimote.sdk.repackaged.retrofit_v1_9_0.retrofit.http.HEAD;

>>>>>>> f45b0a1535c8d716f43c5e4c379c179a9c8c902f
import cz.mendelu.busItWeek.library.SimplePuzzle;
import cz.mendelu.busItWeek.library.StoryLine;
import cz.mendelu.busItWeek.library.Task;

public class SimplePuzzleActivity extends AppCompatActivity {

<<<<<<< HEAD
=======
    // 1. Get widgets references
    private EditText answerTextView;
    private TextView questionView;
    private TextView timerView;

>>>>>>> f45b0a1535c8d716f43c5e4c379c179a9c8c902f
    // 2. Open storyline
    private StoryLine storyLine;

    private SimplePuzzle puzzle;

    @BindView(R.id.question_tv)
    TextView questionView;
    @BindView(R.id.input_layout_answer)
    TextInputLayout answerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_puzzle);
<<<<<<< HEAD

        ButterKnife.bind(this);
=======
        // 1. Get widgets references
        answerTextView = (EditText) findViewById(R.id.answer_tv);
        questionView = (TextView) findViewById(R.id.question);

        // 2. Open storyline
>>>>>>> f45b0a1535c8d716f43c5e4c379c179a9c8c902f
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
        if (currentTask != null) {
            currentTask.getPuzzle();
            puzzle = (SimplePuzzle) currentTask.getPuzzle();
            questionView.setText(puzzle.getQuestion());
        }

    /*
        OPTIE A
        Puzzle puzzle = currentTask.getPuzzle();

        if (puzzle instanceof SimplePuzzle){
           throw new IllegalStateException("No simple puzzle activity");

        }
        OPTIE B
        */

    }

    public void checkAnswer(View view) {
        String answer = answerView.getEditText().getText().toString();
        String correctAnswer = puzzle.getAnswer();

        if (answer.equalsIgnoreCase(correctAnswer)) {
            storyLine.currentTask().finish(true);
            finish();
        } else {
<<<<<<< HEAD
            answerView.setError(getString(R.string.wrong_answer));
=======
            answerTextView.setError(getString(R.string.wrongAnswer));
>>>>>>> f45b0a1535c8d716f43c5e4c379c179a9c8c902f
        }
    }
}
