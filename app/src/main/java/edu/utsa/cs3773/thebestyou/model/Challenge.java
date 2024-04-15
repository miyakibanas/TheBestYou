package edu.utsa.cs3773.thebestyou.model;

import android.os.Parcel;
import android.os.Parcelable;
public class Challenge implements Parcelable {
    private String title;
    private String description;
    private String imageResId;
    private boolean isSelected;

    public Challenge(String title, String description, String imageResId) {
        this.title = title;
        this.description = description;
        this.imageResId = imageResId;
    }

    protected Challenge(Parcel in) {
        title = in.readString();
        description = in.readString();
        imageResId = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(imageResId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Challenge> CREATOR = new Creator<Challenge>() {
        @Override
        public Challenge createFromParcel(Parcel in) {
            return new Challenge(in);
        }

        @Override
        public Challenge[] newArray(int size) {
            return new Challenge[size];
        }
    };

    // Getters
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImageResId() {
        return imageResId;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
