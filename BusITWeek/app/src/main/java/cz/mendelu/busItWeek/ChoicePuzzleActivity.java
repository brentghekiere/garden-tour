package cz.mendelu.busItWeek;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.mendelu.busItWeek.library.ChoicePuzzle;
import cz.mendelu.busItWeek.library.StoryLine;
import cz.mendelu.busItWeek.library.Task;
import cz.mendelu.busItWeek.library.timer.TimerUtil;

public class ChoicePuzzleActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @BindView(R.id.timer)
    TextView timerView;
    @BindView(R.id.question_tv)
    TextView questionView;
    @BindView(R.id.android_material_design_spinner)
    MaterialBetterSpinner spinner;

    private StoryLine storyLine;
    private Map<String, Boolean> mapOfChoices;
    private List<String> spinnerList;
    private Task currentTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_puzzle);

        ButterKnife.bind(this);

        storyLine = StoryLine.open(this, GardernTourStoryLineDBHelper.class);

        currentTask = storyLine.currentTask();

        if (currentTask != null && currentTask.getPuzzle() instanceof ChoicePuzzle) {
            ChoicePuzzle choicePuzzle = (ChoicePuzzle) currentTask.getPuzzle();
            initChoices(choicePuzzle);
            questionView.setText(choicePuzzle.getQuestion());
        }


//        spinner.setOnItemSelectedListener(this);


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, spinnerList);
        spinner.setAdapter(arrayAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (currentTask != null) {
            TimerUtil.startTimer(currentTask.getPuzzle().getPuzzleTime(), timerView, this, GardernTourStoryLineDBHelper.class);
        }
    }

    private void initChoices(ChoicePuzzle choicePuzzle) {

        mapOfChoices = choicePuzzle.getChoices();

        if (mapOfChoices != null && mapOfChoices.size() > 0) {
            spinnerList = new ArrayList<>(mapOfChoices.keySet());
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        checkAnswer(view, i);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void checkAnswer(View view, int i) {
        String answer = spinnerList.get(i);
        if (mapOfChoices.get(answer)) {
            Toast.makeText(this, "Povedlo se", Toast.LENGTH_SHORT).show();
        }
    }
}
