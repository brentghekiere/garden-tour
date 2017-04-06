package cz.mendelu.busItWeek;

import cz.mendelu.busItWeek.R;
import cz.mendelu.busItWeek.library.StoryLineDatabaseHelper;
import cz.mendelu.busItWeek.library.TaskDatabase;
import cz.mendelu.busItWeek.library.builder.StoryLineBuilder;

public class MyDemoStoryLineDBHelper extends StoryLineDatabaseHelper {
    public MyDemoStoryLineDBHelper() {
        super(13);
    }

    @Override
    protected void onCreate(StoryLineBuilder builder) {
        builder.addGPSTask("second one")
                .victoryPoints(15)
                .location(49.209723, 16.614485)
                .radius(80000)
                .simplePuzzle()
                .puzzleTime(50000000)
                .question("What is the meaning of life?")
                .answer("1")
                .puzzleDone()
                .taskDone();
/*
        builder.addBeaconTask("beacon")
                .beacon(1, 9)
                .victoryPoints(15)
                .location(49.209611, 16.614091)
                .simplePuzzle()
                .question("What is the meaning of life?")
                .answer("BusIT Week")
                .puzzleTime(454354354)
                .puzzleDone().taskDone();

        /*
      builder.addCodeTask("Code")
              .location(49.209611, 16.614091)
              .victoryPoints(13)
              .qr("mendel")
              .simplePuzzle()
              .question("What is the best town in the world?")
              .answer("Pittem")
              .puzzleTime(456448564)
              .puzzleDone().taskDone();
              */
    }
}