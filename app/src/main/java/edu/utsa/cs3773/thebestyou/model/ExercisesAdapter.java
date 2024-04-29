// ExercisesAdapter.java

package edu.utsa.cs3773.thebestyou.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.utsa.cs3773.thebestyou.R;

public class ExercisesAdapter extends RecyclerView.Adapter<ExercisesAdapter.ExerciseViewHolder> {

    private List<ChallengeDetail.Exercise> exercises;
    private Context context;
    public List<ChallengeDetail.Exercise> getExercises() {
        return exercises;
    }

    public ExercisesAdapter(List<ChallengeDetail.Exercise> exercises, Context context) {
        this.exercises = exercises;
        this.context = context;
    }

    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exercise, parent, false);
        return new ExerciseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {
        ChallengeDetail.Exercise exercise = exercises.get(position);
        holder.bind(exercise);
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    class ExerciseViewHolder extends RecyclerView.ViewHolder {

        private TextView exerciseTextView;
        private TextView durationTextView;

        private CheckBox exerciseCheckBox;

        public ExerciseViewHolder(@NonNull View itemView) {
            super(itemView);
            exerciseTextView = itemView.findViewById(R.id.exercise_text_view);
            durationTextView = itemView.findViewById(R.id.duration_text_view);
            exerciseCheckBox = itemView.findViewById(R.id.exercise_check_box);
        }

        public void bind(ChallengeDetail.Exercise exercise) {
            exerciseTextView.setText(exercise.getExerciseName());
            durationTextView.setText(exercise.getExerciseDuration());
            // Update the checkbox based on the completion status of the exercise
            exerciseCheckBox.setChecked(exercise.isCompleted());
            // Set an OnCheckedChangeListener to update the completion status of the exercise
            exerciseCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                exercise.setCompleted(isChecked);
            });
        }
    }
}

