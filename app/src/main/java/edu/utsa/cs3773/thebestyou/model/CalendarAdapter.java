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
        // Get the first day of the week for the current month
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    @Override
    public int getCount() {
        if (currentDayOfMonth + daysCount > daysInMonth) {
            // If the challenge spans to the next month, adjust the count
            return daysInMonth + firstDayOfWeekOfMonth - 1; // Add offset for the first day of the month
        } else {
            return daysCount + firstDayOfWeekOfMonth - 1; // Add offset for the first day of the month
        }
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
        TextView challengeView = convertView.findViewById(R.id.challengeTextView);

        int dayOfMonth;
        if (currentDayOfMonth + position < 1) {
            // If the current day plus position is before the start of the month, adjust day of month accordingly
            dayOfMonth = currentDayOfMonth + position + daysInMonth - firstDayOfWeekOfMonth + 1;
        } else if (currentDayOfMonth + position > daysInMonth) {
            // If the current day plus position is after the end of the month, adjust day of month accordingly
            dayOfMonth = currentDayOfMonth + position - daysInMonth - firstDayOfWeekOfMonth + 1;
        } else {
            // Otherwise, use the current day plus position as the day of month
            dayOfMonth = currentDayOfMonth + position - firstDayOfWeekOfMonth + 1;
        }

        dayView.setText(String.valueOf(dayOfMonth));

        int dayIndex = position - (firstDayOfWeekOfMonth - 1); // Adjusted index for the current month
        if (dayOfMonth == currentDayOfMonth) {
            convertView.setBackgroundResource(R.drawable.date_today);
        } else if (dayIndex >= 0 && dayIndex < completionStatusManager.getCompletionStatus().size()) {
            if (completionStatusManager.getCompletionStatus().get(dayIndex)) {
                convertView.setBackgroundResource(R.drawable.calendar_item_background_completed);
            } else {
                convertView.setBackgroundResource(R.drawable.calendar_item_background);
            }
        } else {
            convertView.setBackgroundResource(R.drawable.calendar_item_background);
        }

        if (challenges != null && dayIndex >= 0 && dayIndex < challenges.size()) {
            Challenge challenge = challenges.get(dayIndex);
            challengeView.setText(challenge.getTitle());
            challengeView.setVisibility(View.VISIBLE);
        } else {
            challengeView.setText("");
            challengeView.setVisibility(View.GONE);
        }

        convertView.setOnClickListener(v -> {
            if (dayIndex >= 0) {
                completionStatusManager.updateCompletionStatus(dayIndex, true);
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    public void setCurrentDayOfMonth(int currentDayOfMonth) {
        this.currentDayOfMonth = currentDayOfMonth;
        notifyDataSetChanged(); // Refresh adapter to update views
    }
}
