package edu.utsa.cs3773.thebestyou.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import edu.utsa.cs3773.thebestyou.R;

public class DashboardAdapter extends BaseAdapter {
    private final Context context;
    private final List<DashboardItem> items;

    public DashboardAdapter(Context context, List<DashboardItem> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public DashboardItem getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.dashboard_item, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.imageViewDashboardItem);
        TextView textView = convertView.findViewById(R.id.textViewDashboardItem);

        DashboardItem item = getItem(position);
        imageView.setImageResource(item.getImageResId());
        textView.setText(item.getTitle());

        return convertView;
    }
}

