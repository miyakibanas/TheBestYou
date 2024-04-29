package edu.utsa.cs3773.thebestyou.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class ChallengeCompletionStatusManager implements Parcelable {
    private List<Boolean> completionStatus;
    private int startIndex;

    public ChallengeCompletionStatusManager(int numberOfDays) {
        initializeCompletionStatus(numberOfDays);
        this.startIndex = 0; // Default start index
    }

    private void initializeCompletionStatus(int numberOfDays) {
        completionStatus = new ArrayList<>();
        // Initialize completion status for each day
        for (int i = 0; i < numberOfDays; i++) {
            completionStatus.add(false);
        }
    }

    public void markDayCompleted(int dayIndex) {
        if (dayIndex >= 0 && dayIndex < completionStatus.size()) {
            completionStatus.set(dayIndex, true);
        } else {
            // Handle invalid day index
            throw new IndexOutOfBoundsException("Invalid day index: " + dayIndex);
        }
    }

    public List<Boolean> getCompletionStatus() {
        return completionStatus;
    }

    public void updateCompletionStatus(int day, boolean isCompleted) {
        completionStatus.set(day, isCompleted);
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    // Parcelable implementation

    protected ChallengeCompletionStatusManager(Parcel in) {
        completionStatus = new ArrayList<>();
        in.readList(completionStatus, Boolean.class.getClassLoader());
        startIndex = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(completionStatus);
        dest.writeInt(startIndex);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ChallengeCompletionStatusManager> CREATOR = new Creator<ChallengeCompletionStatusManager>() {
        @Override
        public ChallengeCompletionStatusManager createFromParcel(Parcel in) {
            return new ChallengeCompletionStatusManager(in);
        }

        @Override
        public ChallengeCompletionStatusManager[] newArray(int size) {
            return new ChallengeCompletionStatusManager[size];
        }
    };
}
