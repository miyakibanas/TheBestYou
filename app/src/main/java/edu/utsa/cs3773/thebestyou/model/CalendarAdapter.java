package edu.utsa.cs3773.thebestyou.model;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.util.List;

import edu.utsa.cs3773.thebestyou.R;

public class CalendarAdapter extends BaseAdapter {
    private Context context;
    private int daysCount;
    private List<Boolean> completionStatus;

    public CalendarAdapter(Context context, int daysCount, List<Boolean> completionStatus) {
        this.context = context;
        this.daysCount = daysCount;
        this.completionStatus = completionStatus;
    }

    @Override
    public int getCount() {
        return daysCount;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView dayView;
        if (convertView == null) {
            dayView = new TextView(context);
            dayView.setLayoutParams(new GridView.LayoutParams(GridView.AUTO_FIT, 100));
            dayView.setGravity(Gravity.CENTER);
        } else {
            dayView = (TextView) convertView;
        }

        dayView.setText(String.valueOf(position + 1));

        if (completionStatus.get(position)) {
            dayView.setBackgroundResource(R.drawable.checkmark);
        } else {
            dayView.setBackgroundResource(android.R.color.transparent);
        }

        return dayView;
    }
}
