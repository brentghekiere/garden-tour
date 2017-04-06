package cz.mendelu.busItWeek;

import cz.mendelu.busItWeek.library.StoryLineDatabaseHelper;
import cz.mendelu.busItWeek.library.builder.StoryLineBuilder;

public class GardernTourStoryLineDBHelper extends StoryLineDatabaseHelper {
    public GardernTourStoryLineDBHelper() {
        super(20);
    }

    @Override
    protected void onCreate(StoryLineBuilder builder) {
        builder.addCodeTask("ImageTask")
                .location(49.215806, 16.613889)
                .victoryPoints(5)
                .imageSelectPuzzle()
                .question("What is the picture?")
                .addImage(R.drawable.navigate_arrows_pointing_to_down_red, false)
                .addImage(R.drawable.places_ic_search, false)
                .addImage(R.drawable.help, true)
                .addImage(R.drawable.amu_bubble_shadow, false)
                .puzzleTime(30000)
                .puzzleDone()
                .taskDone();

        builder.addCodeTask("Choice1222")
                .location(49.215806, 16.613889)
                .victoryPoints(5)
                .choicePuzzle()
                .question("The English word for what do you see with the white material?")
                .addChoice("First", false)
                .addChoice("Second", false)
                .addChoice("Third", false)
                .addChoice("Blab", true)
                .puzzleTime(30000)
                .puzzleDone()
                .taskDone();

        builder.addGPSTask("ShuffleWord")
                .victoryPoints(1)
                .location(49.212306, 16.614694)
                .radius(100)
                .simplePuzzle()
                .puzzleTime(30000)
                .question("Try to find the word, out the letters: skcbsoonruteh")
                .answer("stone")
                .puzzleDone()
                .taskDone();

        builder.addBeaconTask("beaconMaze")
                .beacon(1, 9)
                .victoryPoints(2)
                .location(49.212556, 16.614333 )
                .simplePuzzle()
                .question("How many sand particles are there?")
                .answer("9")
                .puzzleTime(30000)
                .puzzleDone()
                .taskDone();

        builder.addCodeTask("QR1")
                .location(49.214222, 16.611667 )
                .victoryPoints(1)
                .qr("QR1")
                .simplePuzzle()
                .question("A celebrated hero, the son of Zeus and Alcmene, possessing exceptional strength")
                .answer("hercules")
                .puzzleTime(30000)
                .puzzleDone().taskDone();



        builder.addCodeTask("QR2")
                .location(49.214722, 16.611528)
                .victoryPoints(3)
                .qr("QR2")
                .simplePuzzle()
                .question("Snow White and the ...")
                .answer("seven dwarfs")
                .puzzleTime(30000)
                .puzzleDone()
                .taskDone();

        builder.addCodeTask("Bridge")
                .location(49.215583, 16.612861)
                .victoryPoints(3)
                .simplePuzzle()
                .question("czech having a very large bridge, which one?")
                .answer("charles bridge")
                .puzzleTime(30000)
                .puzzleDone()
                .taskDone();

        builder.addCodeTask("Serre")
                .location(49.215806, 16.613889)
                .victoryPoints(3)
                .simplePuzzle()
                .question("The English word for what do you see with the white material")
                .answer("sunroom")
                .puzzleTime(30000)
                .puzzleDone()
                .taskDone();


        builder.addCodeTask("Choice")
                .location(49.215806, 16.613889)
                .victoryPoints(5)
                .choicePuzzle()
                .question("The English word for what do you see with the white material?")
                .addChoice("First", false)
                .addChoice("Second", false)
                .addChoice("Third", false)
                .addChoice("Blab", true)
                .puzzleTime(30000)
                .puzzleDone()
                .taskDone();




    }
}
