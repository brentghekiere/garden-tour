package cz.mendelu.busItWeek;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.mendelu.busItWeek.library.ImageSelectPuzzle;
import cz.mendelu.busItWeek.library.StoryLine;
import cz.mendelu.busItWeek.library.Task;
import cz.mendelu.busItWeek.library.timer.TimerUtil;

public class ImageSelectPuzzleActivity extends AppCompatActivity {

    @BindView(R.id.timer)
    TextView timer;
    @BindView(R.id.question_tv)
    TextView questionView;
    @BindView(R.id.grid_view)
    GridView gridView;

    private StoryLine storyLine;
    private ImageSelectPuzzle puzzle;
    private Task currentTask;
    List<Integer> listOfItems;

    private ImageSelectListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_select_puzzle);

        ButterKnife.bind(this);
        storyLine = StoryLine.open(this, GardernTourStoryLineDBHelper.class);
        currentTask = storyLine.currentTask();

        if (currentTask != null && currentTask.getPuzzle() instanceof ImageSelectPuzzle) {
            puzzle = (ImageSelectPuzzle) currentTask.getPuzzle();

            listOfItems = new ArrayList<>();

            for (Map.Entry<Integer, Boolean> entry : puzzle.getImages().entrySet()) {
                listOfItems.add(entry.getKey());
            }

            adapter = new ImageSelectListAdapter(this, R.layout.image_select_list_row, listOfItems);
            gridView.setAdapter(adapter);
            gridView.setOnItemClickListener(adapter);
        } ;

    }

    public class ImageSelectListAdapter extends ArrayAdapter<Integer> implements AdapterView.OnItemClickListener {

        public ImageSelectListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Integer> objects) {
            super(context, resource, objects);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.image_select_list_row, null);
            }

            Integer image = getItem(position);

            if (image != null) {
                ImageView imageView = (ImageView) convertView.findViewById(R.id.image);
                imageView.setImageResource(image);
            }

            return convertView;
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Integer image = listOfItems.get(position);
            if (puzzle.getImages().get(image)) {
                finish();
            } else {
                DialogUtils.createAlertDialog(ImageSelectPuzzleActivity.this, "Wrong picture!", "You chose a wrong picture");
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        currentTask.getPuzzle();

        questionView.setText(puzzle.getQuestion());
        TimerUtil.startTimer(puzzle.getPuzzleTime(), timer, this, GardernTourStoryLineDBHelper.class);

    }
}
