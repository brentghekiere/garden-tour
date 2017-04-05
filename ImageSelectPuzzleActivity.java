package busit.busitgroup3;

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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cz.mendelu.busItWeek.library.SimplePuzzle;
import cz.mendelu.busItWeek.library.StoryLine;
import cz.mendelu.busItWeek.library.Task;
import cz.mendelu.busItWeek.library.timer.TimerUtil;

public class ImageSelectPuzzleActivity extends AppCompatActivity {
    private TextView counter;
    private TextView question;
    private ListView listView;

    private StoryLine storyLine;
    private SimplePuzzle puzzle;
    private Task currentTask;

    private ImageSelectListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_select_puzzle);

        storyLine = StoryLine.open(this, MyDemoStoryLineDBHelper.class);

        currentTask = storyLine.currentTask();

        counter = (TextView) findViewById(R.id.counter);
        question = (TextView) findViewById(R.id.question);
        listView = (ListView) findViewById(R.id.lijstje);
        puzzle = (SimplePuzzle) currentTask.getPuzzle();

        List<Integer> listOfItems = new ArrayList<>();

  /*      for (Map.Entry<Integer, Boolean> entry : puzzle.getImages().entrySet) {
            listOfItems.add(entry.getKey());
        }
*/
        adapter = new ImageSelectListAdapter(this, R.layout.image_select_list_row, listOfItems);
        listView.setAdapter(adapter);
        // tvolgende is voor dienen toast
        listView.setOnItemClickListener(adapter);
        // KINDA A HACK? downloaden van opt internet
        // listView.setListViewHeightBasedOnChildren(listView);
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
            Toast.makeText(getContext(),"message",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        currentTask.getPuzzle();

        question.setText(puzzle.getQuestion());

        TimerUtil.startTimer(puzzle.getPuzzleTime(), counter, this, MyDemoStoryLineDBHelper.class);

    }
}
