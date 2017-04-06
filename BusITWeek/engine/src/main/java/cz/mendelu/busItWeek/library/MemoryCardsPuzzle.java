package cz.mendelu.busItWeek.library;

import android.content.ContentValues;
import android.util.Log;

import static java.lang.String.format;

/**
 * Created by admin on 06.04.2017.
 */

public class MemoryCardsPuzzle extends Puzzle {

    public static final String TAG = MemoryCardsPuzzle.class.getSimpleName();

    static final int TYPE_VALUE = 5;
//
//    static final String MEMORY_CARDS_TABLE = "memory";
//
//    static final String MEMORY_CARDS_ID = BaseColumns._ID;
//
//    static final String MEMORY_CARDS_PUZZLE_ID = "puzzle_id";

//    static final String MEMORY_CARDS_RESOURCE = "resource_id";

//    private int[] images;


    public MemoryCardsPuzzle(ContentValues contentValues) {
        super(contentValues);
//        this.images = images;
        Log.d(TAG, format("Create: %s, images: %s", contentValues));
    }

//    /**
//     * Map of images. Map is orderd as choices was created.
//     * This map has fix order of items.
//     * @return Map contains <code>key</code> as image resource as and <code>value</code> is answer.
//     */
//    public int[] getImages() {
//        return images;
//    }

}
