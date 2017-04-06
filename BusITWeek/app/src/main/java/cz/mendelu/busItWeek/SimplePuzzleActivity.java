package cz.mendelu.busItWeek;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.mendelu.busItWeek.library.SimplePuzzle;
import cz.mendelu.busItWeek.library.StoryLine;
import cz.mendelu.busItWeek.library.Task;
import cz.mendelu.busItWeek.library.timer.TimerUtil;

public class SimplePuzzleActivity extends AppCompatActivity {

    // 2. Open storyline
    private StoryLine storyLine;

    private SimplePuzzle puzzle;

    @BindView(R.id.timer)
    TextView timerView;
    @BindView(R.id.answer_btn)
    Button button;
    @BindView(R.id.question_tv)
    TextView questionView;
    @BindView(R.id.input_layout_answer)
    TextInputLayout answerView;

    int attempts = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_puzzle);

        ButterKnife.bind(this);

        storyLine = StoryLine.open(this, GardernTourStoryLineDBHelper.class);
        Task currentTask = storyLine.currentTask();
        if (currentTask != null) {
            TimerUtil.startTimer(currentTask.getPuzzle().getPuzzleTime(), timerView, this, GardernTourStoryLineDBHelper.class);
        }
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
        if (puzzle != null) {
            String correctAnswer = puzzle.getAnswer();

            if (answer.equalsIgnoreCase(correctAnswer)) {
                storyLine.currentTask().finish(true);
                finish();
            } else {
                if (attempts > 1) {
                    storyLine.currentTask().finish(false);
                    DialogUtils.createAlertDialog(SimplePuzzleActivity.this, "Wrong answer!", "You didn't answer correctly.").show();
                    finish();

                } else {
                    answerView.setError(getString(R.string.wrong_answer));
                }
                attempts++;


            }
        }
    }
}
