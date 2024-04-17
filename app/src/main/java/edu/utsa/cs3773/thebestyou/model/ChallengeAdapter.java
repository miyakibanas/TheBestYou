package edu.utsa.cs3773.thebestyou.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.utsa.cs3773.thebestyou.R;

public class ChallengeAdapter extends RecyclerView.Adapter<ChallengeAdapter.ChallengeViewHolder> {
    private List<Challenge> challengeList;
    private Context context;
    private OnChallengeClickListener onChallengeClickListener;

    public interface OnChallengeClickListener {
        void onChallengeClick(Challenge challenge);
    }

    public ChallengeAdapter(Context context, List<Challenge> challengeList, OnChallengeClickListener listener) {
        this.context = context;
        this.challengeList = challengeList;
        this.onChallengeClickListener = listener;
    }

    @Override
    public ChallengeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.challenge_item, parent, false);
        return new ChallengeViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(ChallengeViewHolder holder, int position) {
        Challenge challenge = challengeList.get(position);
        holder.bind(challenge);

        if (challenge.isSelected()) {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.selected_item_background));
        } else {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.normal_item_background));
        }

        holder.itemView.setOnClickListener(view -> {
            toggleItemSelection(position);
            onChallengeClickListener.onChallengeClick(challenge);
        });
    }

    @Override
    public int getItemCount() {
        return challengeList.size();
    }

    private void toggleItemSelection(int position) {
        Challenge challenge = challengeList.get(position);
        challenge.setSelected(!challenge.isSelected());
        notifyItemChanged(position);
    }

    public List<Challenge> getAllChallenges() {
        return new ArrayList<>(challengeList); 
    }

    private int getDrawableResourceId(String imageName) {
        return context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
    }

    public class ChallengeViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleTextView;
        TextView subtitleTextView;
        ChallengeAdapter adapter;

        public ChallengeViewHolder(View itemView, ChallengeAdapter adapter) {
            super(itemView);
            this.adapter = adapter;
            imageView = itemView.findViewById(R.id.imageViewChallenge);
            titleTextView = itemView.findViewById(R.id.textViewChallengeTitle);
            subtitleTextView = itemView.findViewById(R.id.textViewChallengeSubtitle);
        }

        public void bind(Challenge challenge) {
            titleTextView.setText(challenge.getTitle());
            subtitleTextView.setText(challenge.getDescription());
            imageView.setImageResource(adapter.getDrawableResourceId(challenge.getImageResId()));
        }
    }
}


