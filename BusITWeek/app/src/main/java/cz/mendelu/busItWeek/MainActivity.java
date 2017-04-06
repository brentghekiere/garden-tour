package cz.mendelu.busItWeek;

import android.*;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageStats;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import cz.mendelu.busItWeek.library.StoryLine;
import cz.mendelu.busItWeek.library.Task;

import cz.mendelu.busItWeek.R;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final int REQUEST_PERMISSION = 100;
    private EditText firstNumber;
    private EditText secondNumber;
    private TextView resultView;
    private StoryLine storyLine;
    private TextView taskName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        storyLine = StoryLine.open(this, MyDemoStoryLineDBHelper.class);

        taskName = (TextView) findViewById(R.id.taskName);

        boolean hasPermission =
                (ContextCompat.checkSelfPermission(this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION))
                        == PackageManager.PERMISSION_GRANTED;
        if (!hasPermission) {
// request it
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{
                            android.Manifest.permission.ACCESS_FINE_LOCATION,
                    },
                    REQUEST_PERMISSION
            );
        }
    }

    public void addTwoNumbers(View view) {
        Log.d(TAG, "call method addTwoNumbers");
        double a = Double.parseDouble(firstNumber.getText().toString());
        double b = Double.parseDouble(secondNumber.getText().toString());
        resultView.setText(Double.toString(a + b));
    }


    @Override
    protected void onResume() {
        super.onResume();
        Task task = storyLine.currentTask();
        if (task == null) {
            taskName.setText(getString(R.string.no_tasks));
        } else {
            taskName.setText(task.getName());
        }
    }

    public void showPuzzle(View view) {
        Intent i = new Intent(this, MapActivity.class);
        startActivity(i);
    }
}
