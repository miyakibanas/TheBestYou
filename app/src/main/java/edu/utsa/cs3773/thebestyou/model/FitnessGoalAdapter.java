package edu.utsa.cs3773.thebestyou.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.utsa.cs3773.thebestyou.R;

public class FitnessGoalAdapter extends BaseAdapter {
    private Context context;
    private List<FitnessGoal> fitnessGoals;
    private List<Integer> selectedPositions = new ArrayList<>();

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
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_fitness_goal, parent, false);
        }
        FitnessGoal goal = fitnessGoals.get(position);

        ImageView imageView = convertView.findViewById(R.id.imageViewGoal);
        TextView textView = convertView.findViewById(R.id.textViewGoalName);

        imageView.setImageResource(goal.getImageResId());
        textView.setText(goal.getName());
        convertView.setAlpha(selectedPositions.contains(position) ? 0.5f : 1.0f);

        return convertView;
    }

    public void toggleItemSelection(int position) {
        if (selectedPositions.contains(position)) {
            selectedPositions.remove(Integer.valueOf(position));
        } else {
            selectedPositions.add(position);
        }
        notifyDataSetChanged();
    }

    public List<Integer> getSelectedPositions() {
        return selectedPositions;
    }
}
