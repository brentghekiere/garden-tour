package cz.mendelu.busItWeek;

import android.content.Context;
import android.widget.ArrayAdapter;

/**
 * Created by admin on 07.04.2017.
 */

public class GridViewAdapter extends ArrayAdapter {
    public GridViewAdapter(Context context, int resource) {
        super(context, resource);
    }
//    private Context context;
//    private int layoutResourceId;
//    private ArrayList data = new ArrayList<>();
//
//    public GridViewAdapter(Context context, int layoutResourceId, List<Integer> imagesIds) {
//        super(context, layoutResourceId, data);
//        this.layoutResourceId = layoutResourceId;
//        this.context = context;
//        this.data = data;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        View row = convertView;
//        ViewHolder holder = null;
//
//        if (row == null) {
//            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
//            row = inflater.inflate(layoutResourceId, parent, false);
//            holder = new ViewHolder();
//            holder.image = (ImageView) row.findViewById(R.id.image);
//            row.setTag(holder);
//        } else {
//            holder = (ViewHolder) row.getTag();
//        }
//
//        ImageItem item = data.get(position);
//        holder.imageTitle.setText(item.getTitle());
//        holder.image.setImageBitmap(item.getImage());
//        return row;
//    }
//
//    static class ViewHolder {
//        ImageView image;
//    }
}