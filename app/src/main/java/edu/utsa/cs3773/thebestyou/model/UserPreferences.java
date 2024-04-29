package edu.utsa.cs3773.thebestyou.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class UserPreferences implements Parcelable {
    private final List<String> fitnessGoals;
    private final String fitnessLevel;

    public UserPreferences(List<String> fitnessGoals, String fitnessLevel) {
        this.fitnessGoals = fitnessGoals;
        this.fitnessLevel = fitnessLevel;
    }

    protected UserPreferences(Parcel in) {
        fitnessGoals = in.createStringArrayList();
        fitnessLevel = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(fitnessGoals);
        dest.writeString(fitnessLevel);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UserPreferences> CREATOR = new Creator<UserPreferences>() {
        @Override
        public UserPreferences createFromParcel(Parcel in) {
            return new UserPreferences(in);
        }

        @Override
        public UserPreferences[] newArray(int size) {
            return new UserPreferences[size];
        }
    };

    // Getters
    public List<String> getFitnessGoals() {
        return fitnessGoals;
    }

    public String getFitnessLevel() {
        return fitnessLevel;
    }
}

