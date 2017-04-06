package cz.mendelu.busItWeek;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;
import cz.mendelu.busItWeek.library.StoryLine;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int REQUEST_PERMISSION = 100;
    private StoryLine storyLine;
   // public static int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        storyLine = StoryLine.open(this, GardernTourStoryLineDBHelper.class);

        ButterKnife.bind(this);

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

    @Override
    protected void onResume() {
        super.onResume();
//        Task task = storyLine.currentTask();
//        if (task == null) {
//            taskName.setText(getString(R.string.no_tasks));
//        } else {
//            taskName.setText(task.getName());
//        }
    }

    public void startGame(View view) {
//        Intent i = new Intent(this, cz.mendelu.busItWeek.MapActivity.class);
        Intent i = new Intent(this, ImageSelectPuzzleActivity.class);
        startActivity(i);
    }
}
