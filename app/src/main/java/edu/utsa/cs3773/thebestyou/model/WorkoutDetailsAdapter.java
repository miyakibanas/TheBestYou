package edu.utsa.cs3773.thebestyou.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.utsa.cs3773.thebestyou.R;

public class WorkoutDetailsAdapter extends RecyclerView.Adapter<WorkoutDetailsAdapter.ViewHolder> {

    private final List<ChallengeDetail.Exercise> exercises;

    public WorkoutDetailsAdapter(List<ChallengeDetail.Exercise> exercises) {
        this.exercises = exercises;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.exercise_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChallengeDetail.Exercise exercise = exercises.get(position);
        holder.exerciseName.setText(exercise.getExerciseName());
        holder.exerciseDuration.setText(String.valueOf(exercise.getExerciseDuration()));
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView exerciseName;
        public TextView exerciseDuration;

        public ViewHolder(View itemView) {
            super(itemView);
            exerciseName = itemView.findViewById(R.id.exercise_name);
            exerciseDuration = itemView.findViewById(R.id.exercise_duration);
        }
    }
}
