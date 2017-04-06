package cz.mendelu.busItWeek.library.memorycards;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by admin on 06.04.2017.
 */

public class MemoryCardAdapter extends BaseAdapter {

    private Context mContext;
    private Integer[] pieces;
    private List imageViews;
    private int piece_up = -1;

    public MemoryCardAdapter(Context context) {
        mContext = context;
        List ipieces = new ArrayList();
        for (int i = 0; i < 12; i++) {
            ipieces.add(i);
            ipieces.add(i);
        }
        Collections.shuffle(ipieces);
        pieces = (Integer[]) ipieces.toArray(new Integer[0]);
        _createImageViews();
    }

    private void _createImageViews() {
        imageViews = new ArrayList();
        for (int position = 0; position < getCount(); position++) {
            ImageView imageView;

            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(60, 80));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(2, 2, 2, 2);

//            imageView.setImageResource(R.drawable.tree);
            imageViews.add(imageView);

//            installClick(position);
        }
    }

    public int getCount() {
        return 24; //mThumbIds.length;
    }

    public Object getItem(int position) {
        return imageViews.get(position);
    }

    public long getItemId(int position) {
        return pieces[position].longValue();
    }

    // create a new ImageView for each item referenced by the Adapter
    public synchronized View getView(int position, View convertView, ViewGroup parent) {
        return (ImageView) imageViews.get(position);
    }

//    public void installClick(int position) {
//        // final int pos = position;
//        final ImageAdapter self = this;
//        Log.d("ImageAdapter", "click *" + Integer.toString(position));
//        ImageView imageView = (ImageView) imageViews.get(position);
//        imageView.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                int pos = imageViews.indexOf((ImageView) v);
//                Log.d("ImageAdapter", "click!" + Integer.toString(pos));
//                show(pos);
//
//
//                // FIXME: UI update
//                // http://developer.android.com/resources/articles/timed-ui-updates.html
//                if (piece_up == -1 || piece_up == pos) {
//                    // first click
//                    piece_up = pos;
//                } else {
//                    // second click
//                    if (pieces[pos] == pieces[piece_up]) {
//                        // ok, it's equal
//                        Toast.makeText(mContext, "good!", 2).show();
//                        //
//                        // remove click handler
//                        removeClick(pos);
//                        removeClick(piece_up);
//
//                    } else {
//                        // try again
//                        int aux[] = {piece_up, pos};
//                    }
//
//                    piece_up = -1;
//                }
//
//            }
//        });
//    }
//
//    public void removeClick(int position) {
//        ImageView aux;
//        aux = (ImageView) imageViews.get(position);
//        aux.setOnClickListener(null);
//    }
//
//    public void hide(int position) {
//        ImageView img;
//        img = (ImageView) imageViews.get(position);
//        int piece = pieces[position];
//        img.setImageResource(R.drawable.tree);
//    }
//
//    public void show(int position) {
//        ImageView img;
//        img = (ImageView) imageViews.get(position);
//        int piece = pieces[position];
//        img.setImageResource(mThumbIds[piece]);
//
//    }

    //
    // references to our images

}
