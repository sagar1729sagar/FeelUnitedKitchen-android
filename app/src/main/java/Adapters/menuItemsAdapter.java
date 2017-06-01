package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import Models.Item;
import ssapps.com.feelunitedkitchen.R;

/**
 * Created by sagar on 02/06/17.
 */
public class menuItemsAdapter extends BaseAdapter {

    private ArrayList<Item> items = new ArrayList<>();
    private Context mContext;

    public menuItemsAdapter(ArrayList<Item> items, Context mContext) {
        this.items = items;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;

        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.menu_item, null);

            ViewHolder viewHolder = new ViewHolder();
            viewHolder.image = (ImageView) rowView
                    .findViewById(R.id.image_menu);
            rowView.setTag(viewHolder);
        }

        ViewHolder holder = (ViewHolder) rowView.getTag();

        Picasso.with(mContext).load(items.get(position).getItemUrl()).into(holder.image);
        return convertView;
    }


    static class ViewHolder {
        public ImageView image;
    }

}
