package cz.mendelu.busItWeek;

import cz.mendelu.busItWeek.R;
import cz.mendelu.busItWeek.library.StoryLineDatabaseHelper;
import cz.mendelu.busItWeek.library.TaskDatabase;
import cz.mendelu.busItWeek.library.builder.StoryLineBuilder;

public class MyDemoStoryLineDBHelper extends StoryLineDatabaseHelper {
    public MyDemoStoryLineDBHelper() {
        super(10);
    }

    @Override
    protected void onCreate(StoryLineBuilder builder) {
      /*  builder.addGPSTask("second one")
                .victoryPoints(15)
                .location(10, 10)
                .radius(30)
                .imageSelectPuzzle()
                .puzzleTime(50000000)
                .question("Choose an image")
                .addImage(R.drawable.a, true)
                .addImage(R.drawable.b, false)
                .addImage(R.drawable.c, false)
                .puzzleDone()
                .taskDone();

      builder.addBeaconTask("beacon")
              .beacon(1,9)
              .victoryPoints(15)
              .location(49.209611, 16.614091)
              .simplePuzzle()
              .question("What is the meaning of life?")
              .answer("BusIT Week")
              .puzzleTime(454354354)
              .puzzleDone().taskDone();
               */

      builder.addCodeTask("Code")
              .location(49.209611, 16.614091)
              .victoryPoints(13)
              .qr("mendel")
              .simplePuzzle()
              .question("What is the best town in the world?")
              .answer("Pittem")
              .puzzleTime(456448564)
              .puzzleDone().taskDone();
    }
}