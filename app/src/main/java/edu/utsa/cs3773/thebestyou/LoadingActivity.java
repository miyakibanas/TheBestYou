package edu.utsa.cs3773.thebestyou;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import edu.utsa.cs3773.thebestyou.model.FitnessGoal;


public class LoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        ImageView gifImageView1 = findViewById(R.id.gifImageView1);
        Glide.with(this).asGif().load("file:///android_asset/run_loading.gif").into(gifImageView1);

        ArrayList<FitnessGoal> selectedGoals = getIntent().getParcelableArrayListExtra("selectedGoals");

        gifImageView1.postDelayed(() -> {
            Intent intent = new Intent(LoadingActivity.this, ChallengesActivity.class);
            intent.putParcelableArrayListExtra("selectedGoals", selectedGoals);
            startActivity(intent);
            finish();
        }, 6000);
    }
}
