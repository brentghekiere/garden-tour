package cz.mendelu.busItWeek;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MemoryCardsActivity extends AppCompatActivity {

    @BindView(R.id.grid_view)
    GridView memoryCards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_cards);
        ButterKnife.bind(this);
    }
}
