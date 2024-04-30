package edu.utsa.cs3773.thebestyou;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.utsa.cs3773.thebestyou.model.Challenge;

import edu.utsa.cs3773.thebestyou.model.ChallengeAdapter;
import edu.utsa.cs3773.thebestyou.model.DashboardAdapter;
import edu.utsa.cs3773.thebestyou.model.DashboardItem;
import edu.utsa.cs3773.thebestyou.utils.PreferenceManager;

public class DashboardActivity extends BaseActivity {
    private PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        preferenceManager = new PreferenceManager(this);

        GridView gridView = findViewById(R.id.gridViewDashboard);
        List<DashboardItem> items = getDashboardItems();
        DashboardAdapter adapter = new DashboardAdapter(this, items);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener((parent, view, position, id) -> {
            DashboardItem item = items.get(position);
            navigateToActivity(item.getTitle());
        });
        ArrayList<Challenge> selectedChallenges = getIntent().getParcelableArrayListExtra("selectedChallenges");

        // Log the selected challenges along with their start dates
        if (selectedChallenges != null && !selectedChallenges.isEmpty()) {
            for (Challenge challenge : selectedChallenges) {
                Log.d("DashboardActivity", "Selected Challenge: " + challenge.getTitle() + ", Start Date: " + challenge.getStartDate());
                // You can log more details about the challenge if needed
            }
        } else {
            Log.d("DashboardActivity", "No challenges selected.");
        }

        Button btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(v -> {
            logoutUser();
        });
    }

    private List<DashboardItem> getDashboardItems() {
        List<DashboardItem> items = new ArrayList<>();
        items.add(new DashboardItem(R.drawable.ic_challenges, "CHALLENGES"));
        items.add(new DashboardItem(R.drawable.ic_progress, "PROGRESS"));
        items.add(new DashboardItem(R.drawable.ic_rewards, "REWARDS"));
        items.add(new DashboardItem(R.drawable.ic_profile, "PROFILE"));
        return items;
    }

    private void navigateToActivity(String title) {
        // Based on the title, decide which activity to navigate to.
        if (title.equals("CHALLENGES")) {
            ArrayList<Challenge> selectedChallenges = getIntent().getParcelableArrayListExtra("selectedChallenges");
            Intent intent = new Intent(DashboardActivity.this, SelectedChallengesActivity.class);
            intent.putParcelableArrayListExtra("selectedChallenges", selectedChallenges);
            startActivity(intent);
        } else if (title.equals("PROGRESS")) {
                // Navigate to Progress Activity
            ArrayList<Challenge> selectedChallenges = getIntent().getParcelableArrayListExtra("selectedChallenges");

            // Create an intent to navigate to ProgressActivity
            Intent intent = new Intent(DashboardActivity.this, ProgressActivity.class);

            // Put the list of selected challenges into the intent
            intent.putParcelableArrayListExtra("selectedChallenges", selectedChallenges);

            // Start the ProgressActivity
            startActivity(intent);

        }else if (title.equals("REWARDS")) {

            // Navigate to Rewards Activity
            Intent intent = new Intent(DashboardActivity.this, RewardsActivity.class);
            startActivity(intent);

        } else if (title.equals("PROFILE")) {
            // Navigate to Profile Activity
            Intent intent = new Intent(this, UserProfileSettingActivity.class);
            intent.putExtra("isFromDashboard", true);
            startActivity(intent);
        }
    }
    private void logoutUser() {
        UserSessionManager.clearUserSession();

        Intent intent = new Intent(DashboardActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}

