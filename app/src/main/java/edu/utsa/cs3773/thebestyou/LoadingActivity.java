package edu.utsa.cs3773.thebestyou;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import edu.utsa.cs3773.thebestyou.model.FitnessGoal;
import edu.utsa.cs3773.thebestyou.model.UserPreferences;


public class LoadingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        ImageView gifImageView1 = findViewById(R.id.gifImageView1);
        Glide.with(this).asGif().load("file:///android_asset/run_loading.gif").into(gifImageView1);

        ArrayList<FitnessGoal> selectedGoals = getIntent().getParcelableArrayListExtra("selectedGoals");
        UserPreferences userPreferences = getIntent().getParcelableExtra("UserPreferences");

        gifImageView1.postDelayed(() -> {
            if (selectedGoals != null && !selectedGoals.isEmpty()) {
                Intent intent = new Intent(LoadingActivity.this, ChallengesActivity.class);
                intent.putParcelableArrayListExtra("selectedGoals", selectedGoals);
                intent.putExtra("UserPreferences", userPreferences);
                startActivity(intent);
                finish();
            } else {
                finish();
            }
        }, 6000);
    }
}

