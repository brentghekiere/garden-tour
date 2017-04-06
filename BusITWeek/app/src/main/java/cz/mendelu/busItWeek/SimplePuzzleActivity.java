package cz.mendelu.busItWeek;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.mendelu.busItWeek.library.SimplePuzzle;
import cz.mendelu.busItWeek.library.StoryLine;
import cz.mendelu.busItWeek.library.Task;

public class SimplePuzzleActivity extends AppCompatActivity {

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

        ButterKnife.bind(this);
        storyLine = StoryLine.open(this, MyDemoStoryLineDBHelper.class);
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
            answerView.setError(getString(R.string.wrong_answer));
        }
    }
}
