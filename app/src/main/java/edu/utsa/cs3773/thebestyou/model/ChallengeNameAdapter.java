package edu.utsa.cs3773.thebestyou.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import edu.utsa.cs3773.thebestyou.R;

import edu.utsa.cs3773.thebestyou.model.Challenge;

public class ChallengeNameAdapter extends RecyclerView.Adapter<ChallengeNameAdapter.ChallengeViewHolder> {
    private  List<Challenge> challengeList;
    private final OnChallengeClickListener onChallengeClickListener;
    private Challenge updatedChallenge; // Declare updatedChallenge as a member variable


    public interface OnChallengeClickListener {
        void onChallengeClick(Challenge challenge);
    }

    public ChallengeNameAdapter(List<Challenge> challengeList, OnChallengeClickListener listener) {
        if (challengeList == null) {
            this.challengeList = new ArrayList<>();
        } else {
            this.challengeList = challengeList;
        }
        this.onChallengeClickListener = listener;
    }
    public void updateChallenges(List<Challenge> updatedList) {
        this.challengeList = updatedList;
        notifyDataSetChanged(); // Refresh the adapter's view
    }
    @Override
    public void onBindViewHolder(ChallengeViewHolder holder, int position) {
        Challenge challenge = challengeList.get(position);
        holder.bind(challenge);
        holder.itemView.setOnClickListener(view -> {
            onChallengeClickListener.onChallengeClick(challenge);
        });

        TextView completionStatusTextView = holder.itemView.findViewById(R.id.completionStatusTextView);

        // Check if the current day within the challenge is completed
        if (isDayCompletedForChallenge(challenge)) {
            completionStatusTextView.setText("Workout completed for today!");
        } else {
            completionStatusTextView.setText("You have a workout to complete for today!");
        }
    }

    // Method to check if the current day within the challenge is completed
    private boolean isDayCompletedForChallenge(Challenge challenge) {
        // Get the completion status manager for the challenge
        ChallengeCompletionStatusManager completionStatusManager = challenge.getCompletionStatusManager();

        // Get the current day index
        int currentDayIndex = completionStatusManager.getStartIndex();

        // Check if the current day index is within the range of completion status list
        if (currentDayIndex >= 0 && currentDayIndex < completionStatusManager.getCompletionStatus().size()) {
            // Check if the current day is marked off
            return completionStatusManager.getCompletionStatus().get(currentDayIndex);
        } else {
            // Handle the case where the current day index is out of range
            // This could happen if the start index is not properly set or if the completion status list is empty
            return false;
        }
    }


    @Override
    public int getItemCount() {
        return challengeList.size();
    }
    public void setUpdatedChallenge(Challenge updatedChallenge) {
        this.updatedChallenge = updatedChallenge;
    }
    public void updateChallenges(List<Challenge> updatedList, Challenge updatedChallenge) {
        this.challengeList = updatedList;
        this.updatedChallenge = updatedChallenge; // Set the updatedChallenge
        notifyDataSetChanged(); // Refresh the adapter's view
    }

    public List<Challenge> getAllChallenges() {
        return new ArrayList<>(challengeList);
    }

    public class ChallengeViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;

        public ChallengeViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.ChallengeTitle);
        }

        public void bind(Challenge challenge) {
            titleTextView.setText(challenge.getTitle());
        }
    }
    @Override
    public ChallengeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_challenge, parent, false);
        return new ChallengeViewHolder(itemView);
    }

}
