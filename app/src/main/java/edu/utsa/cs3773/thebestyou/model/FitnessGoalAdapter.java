package edu.utsa.cs3773.thebestyou.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

import edu.utsa.cs3773.thebestyou.R;

public class FitnessGoalAdapter extends BaseAdapter {
    private Context context;
    private List<FitnessGoal> fitnessGoals;

    public FitnessGoalAdapter(Context context, List<FitnessGoal> fitnessGoals) {
        this.context = context;
        this.fitnessGoals = fitnessGoals;
    }

    @Override
    public int getCount() {
        return fitnessGoals.size();
    }

    @Override
    public Object getItem(int position) {
        return fitnessGoals.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_fitness_goal, parent, false);
            holder = new ViewHolder();
            holder.imageView = convertView.findViewById(R.id.imageViewGoal);
            holder.textView = convertView.findViewById(R.id.textViewGoalName);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        FitnessGoal goal = fitnessGoals.get(position);
        holder.imageView.setImageResource(goal.getImageResId());
        holder.textView.setText(goal.getName());
        if (goal.isSelected()) {
            convertView.setBackgroundColor(ContextCompat.getColor(context, R.color.selected_item_background));
        } else {
            convertView.setBackgroundColor(ContextCompat.getColor(context, R.color.normal_item_background));
        }

        return convertView;
    }

    static class ViewHolder {
        ImageView imageView;
        TextView textView;
    }

    public void toggleItemSelection(int position) {
        FitnessGoal goal = fitnessGoals.get(position);
        goal.setSelected(!goal.isSelected());
        notifyDataSetChanged();
    }

}
