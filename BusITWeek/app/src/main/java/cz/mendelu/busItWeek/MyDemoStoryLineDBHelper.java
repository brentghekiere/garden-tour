package cz.mendelu.busItWeek;

import cz.mendelu.busItWeek.library.StoryLineDatabaseHelper;
import cz.mendelu.busItWeek.library.builder.StoryLineBuilder;
import cz.mendelu.busItWeek.library.builder.TaskBuilder;

import static cz.mendelu.busItWeek.library.Puzzle.PuzzleLevel.EASY;

public class MyDemoStoryLineDBHelper extends StoryLineDatabaseHelper {
    public MyDemoStoryLineDBHelper() {
        super(19);
    }

    @Override
    protected void onCreate(StoryLineBuilder builder) {
        builder.addGPSTask("first one")
                .victoryPoints(15)
                .location(49.209723, 16.614485)
                .radius(80000)
                .simplePuzzle()
                .puzzleTime(50000000)
                .question("What is the meaning of life?")
                .answer("1")
                .puzzleDone()
                .taskDone();


        builder.addBeaconTask("beacon")
                .beacon(1, 19)
                .victoryPoints(15)
                .location(500, 16.614091)
                .simplePuzzle()
                .question("What is the meaning of fear?")
                .answer("1")
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



      builder.addCodeTask("Code")
              .location(49.209611, 16.614091)
              .victoryPoints(13)
              .qr("mendel")
              .simplePuzzle()
                  .question("What is the best town in the world?")
                  .answer("Pittem")
                  .puzzleTime(456448564)
                  .puzzleDone()
              .taskDone();

        TaskBuilder tb = (TaskBuilder) builder.addBeaconTask("beacon2")
                .beacon(1,9)
                .victoryPoints(15)
                .location(49.209611, 16.614091)
                .simplePuzzle()
                .level(EASY.getLevel())
                .question("What is the meaning of life?")
                .answer("1")
                .puzzleTime(454354354)
                .puzzleDone();
        tb.taskDone();
*/
    }
}