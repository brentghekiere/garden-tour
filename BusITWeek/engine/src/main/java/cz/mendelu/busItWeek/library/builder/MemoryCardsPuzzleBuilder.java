package cz.mendelu.busItWeek.library.builder;

/**
 * Created by admin on 06.04.2017.
 */

public interface MemoryCardsPuzzleBuilder<T> extends PuzzleBuilder<MemoryCardsPuzzleBuilder, T> {
    MemoryCardsPuzzleBuilder<T> puzzleTime(long time);
    MemoryCardsPuzzleBuilder<T> addImages(int[] resourceIds);
}
