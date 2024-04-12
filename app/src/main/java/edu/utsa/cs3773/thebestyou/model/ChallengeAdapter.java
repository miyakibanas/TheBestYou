package edu.utsa.cs3773.thebestyou.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.utsa.cs3773.thebestyou.R;

public class ChallengeAdapter extends RecyclerView.Adapter<ChallengeAdapter.ChallengeViewHolder> {

    private List<Challenge> challengeList;

    public ChallengeAdapter(List<Challenge> challengeList) {
        this.challengeList = challengeList;
    }

    @Override
    public ChallengeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.challenge_item, parent, false);
        return new ChallengeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ChallengeViewHolder holder, int position) {
        Challenge challenge = challengeList.get(position);
        holder.bind(challenge);
    }

    @Override
    public int getItemCount() {
        return challengeList.size();
    }

    public static class ChallengeViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleTextView;
        TextView subtitleTextView;

        public ChallengeViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewChallenge);
            titleTextView = itemView.findViewById(R.id.textViewChallengeTitle);
            subtitleTextView = itemView.findViewById(R.id.textViewChallengeSubtitle);
        }

        public void bind(Challenge challenge) {
            titleTextView.setText(challenge.getTitle());
            subtitleTextView.setText(challenge.getDescription());

            imageView.setImageResource(challenge.getImageUrl());
        }
    }
}

