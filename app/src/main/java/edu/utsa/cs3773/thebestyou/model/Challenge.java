package edu.utsa.cs3773.thebestyou.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.time.LocalDate;

public class Challenge implements Parcelable {
    private final String title;
    private final String description;
    private final String imageResId;
    public final int numberOfDays;
    private boolean isSelected;
    private LocalDate startDate; // Updated to LocalDate
    // Remove direct reference to completion status fields
    // private boolean[] completionStatus;
    private ChallengeCompletionStatusManager completionStatusManager; // Manage completion status for each day

    public Challenge(String title, String description, String imageResId, int numberOfDays) {
        this.title = title;
        this.description = description;
        this.imageResId = imageResId;
        this.completionStatusManager = new ChallengeCompletionStatusManager(numberOfDays); // Initialize completion status manager
        this.numberOfDays = numberOfDays;
    }

    protected Challenge(Parcel in) {
        title = in.readString();
        description = in.readString();
        imageResId = in.readString();
        isSelected = in.readByte() != 0;
        long epochDay = in.readLong();
        startDate = epochDay != -1 ? LocalDate.ofEpochDay(epochDay) : LocalDate.now();  // Default to current day if -1
        completionStatusManager = in.readParcelable(ChallengeCompletionStatusManager.class.getClassLoader());
        numberOfDays = in.readInt(); // Read numberOfDays from Parcel
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(imageResId);
        dest.writeByte((byte) (isSelected ? 1 : 0));
        dest.writeLong(startDate.toEpochDay()); // Serialize LocalDate to parcel
        dest.writeParcelable(completionStatusManager, flags);
        dest.writeInt(numberOfDays); // Write numberOfDays to Parcel
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

    // Getters and setters
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

    // Update this method to access completion status via completion status manager

    public void markDayCompleted(int dayIndex) {
        completionStatusManager.markDayCompleted(dayIndex);
    }

    // Method to check if a specific day is completed
    public void updateCompletionStatusForDay(int dayIndex, boolean isCompleted) {
        completionStatusManager.updateCompletionStatus(dayIndex, isCompleted);
    }
    public ChallengeCompletionStatusManager getCompletionStatusManager() {
        return completionStatusManager;
    }

    public boolean isDayCompleted(int dayIndex) {
        return completionStatusManager.getCompletionStatus().get(dayIndex);
    }
    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
}
