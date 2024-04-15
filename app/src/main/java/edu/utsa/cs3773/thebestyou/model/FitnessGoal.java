package edu.utsa.cs3773.thebestyou.model;

import android.os.Parcel;
import android.os.Parcelable;

public class FitnessGoal implements Parcelable {
    private int imageResId;
    private String name;
    private boolean isSelected;

    public FitnessGoal(int imageResId, String name) {
        this.imageResId = imageResId;
        this.name = name;
    }

    protected FitnessGoal(Parcel in) {
        imageResId = in.readInt();
        name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(imageResId);
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<FitnessGoal> CREATOR = new Creator<FitnessGoal>() {
        @Override
        public FitnessGoal createFromParcel(Parcel in) {
            return new FitnessGoal(in);
        }

        @Override
        public FitnessGoal[] newArray(int size) {
            return new FitnessGoal[size];
        }
    };

    // Getter methods
    public int getImageResId() {
        return imageResId;
    }

    public String getName() {
        return name;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}