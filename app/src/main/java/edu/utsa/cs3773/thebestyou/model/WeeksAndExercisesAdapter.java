package edu.utsa.cs3773.thebestyou.model;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.utsa.cs3773.thebestyou.R;
import edu.utsa.cs3773.thebestyou.model.ChallengeDetail;

public class WeeksAndExercisesAdapter extends RecyclerView.Adapter<WeeksAndExercisesAdapter.ViewHolder> {

    private List<ChallengeDetail.Week> weeks;
    private Context context;

    public WeeksAndExercisesAdapter(List<ChallengeDetail.Week> weeks, Context context) {
        this.weeks = weeks;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.week_and_exercise_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (weeks == null || weeks.isEmpty()) {
            return;
        }

        ChallengeDetail.Week week = weeks.get(position);
        if (week == null) {
            return;
        }

        holder.weekNumberTextView.setText("Week " + week.getWeekNumber());

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        holder.exercisesRecyclerView.setLayoutManager(layoutManager);
        WorkoutDetailsAdapter adapter = new WorkoutDetailsAdapter(week.getWorkout().getExercises());
        holder.exercisesRecyclerView.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return weeks != null ? weeks.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView weekNumberTextView;
        RecyclerView exercisesRecyclerView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            weekNumberTextView = itemView.findViewById(R.id.challenge_name_text_view);
            exercisesRecyclerView = itemView.findViewById(R.id.weeks_recycler_view);
        }
    }
}
