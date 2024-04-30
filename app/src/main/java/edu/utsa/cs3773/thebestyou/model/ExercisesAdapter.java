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

    public boolean areAllExercisesCompleted() {
        for (ChallengeDetail.Exercise exercise : exercises) {
            if (!exercise.isCompleted()) {
                return false;
            }
        }
        return true;
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
            exerciseCheckBox.setChecked(exercise.isCompleted());
            exerciseCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                exercise.setCompleted(isChecked);
            });
        }
    }
}
