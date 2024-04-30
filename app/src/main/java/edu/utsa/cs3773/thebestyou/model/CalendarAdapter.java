package edu.utsa.cs3773.thebestyou.model;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

import edu.utsa.cs3773.thebestyou.R;
import edu.utsa.cs3773.thebestyou.model.Challenge;
import edu.utsa.cs3773.thebestyou.model.ChallengeCompletionStatusManager;

public class CalendarAdapter extends BaseAdapter {

    private final Context context;
    private final int daysCount;
    private final List<Challenge> challenges;
    private final ChallengeCompletionStatusManager completionStatusManager;
    private int currentDayOfMonth;
    private int firstDayOfWeekOfMonth;
    private int daysInMonth;

    public CalendarAdapter(Context context, int daysCount, List<Challenge> challenges, ChallengeCompletionStatusManager completionStatusManager) {
        this.context = context;
        this.daysCount = daysCount;
        this.challenges = challenges;
        this.completionStatusManager = completionStatusManager;
        Calendar cal = Calendar.getInstance();
        this.currentDayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        this.firstDayOfWeekOfMonth = getFirstDayOfWeekOfMonth(cal);
        this.daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    private int getFirstDayOfWeekOfMonth(Calendar cal) {
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return cal.get(Calendar.DAY_OF_WEEK) - 1; // Adjust to make Sunday (0), Monday (1), etc.
    }

    @Override
    public int getCount() {
        return daysInMonth + firstDayOfWeekOfMonth - 1; // Show all days in month plus padding for start of the month
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
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_calendar_day, parent, false);
        }

        TextView dayView = convertView.findViewById(R.id.dayTextView);

        int dayOfMonth = position - firstDayOfWeekOfMonth + 1;
        if (dayOfMonth < 1 || dayOfMonth > daysInMonth) {
            // Disable days not in the current month
            convertView.setVisibility(View.INVISIBLE);
        } else {
            convertView.setVisibility(View.VISIBLE);
            dayView.setText(String.valueOf(dayOfMonth));

            if (dayOfMonth == currentDayOfMonth) {
                convertView.setBackgroundResource(R.drawable.date_today);
            } else {
                convertView.setBackgroundResource(R.drawable.calendar_item_background);
            }

            int dayIndex = dayOfMonth - 1; // Zero-based index for days
            if (dayIndex < completionStatusManager.getCompletionStatus().size()) {
                if (completionStatusManager.getCompletionStatus().get(dayIndex)) {
                    convertView.setBackgroundResource(R.drawable.calendar_item_background_completed);
                }
            }

            convertView.setOnClickListener(v -> {
                if (dayIndex >= 0) {
                    completionStatusManager.updateCompletionStatus(dayIndex, true);
                    notifyDataSetChanged();
                }
            });
        }

        return convertView;
    }

    public void setCurrentDayOfMonth(int currentDayOfMonth) {
        this.currentDayOfMonth = currentDayOfMonth;
        notifyDataSetChanged();
    }
}