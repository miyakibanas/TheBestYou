// ChallengeDetail.java
package edu.utsa.cs3773.thebestyou.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class ChallengeDetail implements Parcelable {
    private final String challengeName;
    private final String goalName;
    private final Level level;

    public ChallengeDetail(String challengeName, String goalName, Level level) {
        this.challengeName = challengeName;
        this.goalName = goalName;
        this.level = level;
    }

    protected ChallengeDetail(Parcel in) {
        challengeName = in.readString();
        goalName = in.readString();
        level = in.readParcelable(Level.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(challengeName);
        dest.writeString(goalName);
        dest.writeParcelable(level, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ChallengeDetail> CREATOR = new Creator<ChallengeDetail>() {
        @Override
        public ChallengeDetail createFromParcel(Parcel in) {
            return new ChallengeDetail(in);
        }

        @Override
        public ChallengeDetail[] newArray(int size) {
            return new ChallengeDetail[size];
        }
    };

    public String getChallengeName() {
        return challengeName;
    }

    public String getGoalName() {
        return goalName;
    }

    public Level getLevel() {
        return level;
    }

    public static class Level implements Parcelable {
        private final String levelName;
        private final List<Week> weeks;

        public Level(String levelName, List<Week> weeks) {
            this.levelName = levelName;
            this.weeks = new ArrayList<>(weeks);
        }

        protected Level(Parcel in) {
            levelName = in.readString();
            weeks = new ArrayList<>();
            in.readTypedList(weeks, Week.CREATOR);
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(levelName);
            dest.writeTypedList(weeks);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<Level> CREATOR = new Creator<Level>() {
            @Override
            public Level createFromParcel(Parcel in) {
                return new Level(in);
            }

            @Override
            public Level[] newArray(int size) {
                return new Level[size];
            }
        };


        public String getLevelName() {
            return levelName;
        }

        public List<Week> getWeeks() {
            return weeks;
        }
    }

    public static class Week implements Parcelable {
        private final int weekNumber;
        private final Workout workout;

        public Week(int weekNumber, Workout workout) {
            this.weekNumber = weekNumber;
            this.workout = workout;
        }

        protected Week(Parcel in) {
            weekNumber = in.readInt();
            workout = in.readParcelable(Workout.class.getClassLoader());
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(weekNumber);
            dest.writeParcelable(workout, flags);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<Week> CREATOR = new Creator<Week>() {
            @Override
            public Week createFromParcel(Parcel in) {
                return new Week(in);
            }

            @Override
            public Week[] newArray(int size) {
                return new Week[size];
            }
        };

        public int getWeekNumber() {
            return weekNumber;
        }

        public Workout getWorkout() {
            return workout;
        }

        public List<Exercise> getExercises() {
            return workout.getExercises();
        }

    }

    public static class Workout implements Parcelable {
        private final String workoutName;
        private final String workoutDescription;
        private final String overallDuration;
        private final List<Exercise> exercises;

        public Workout(String workoutName, String workoutDescription, String overallDuration, List<Exercise> exercises) {
            this.workoutName = workoutName;
            this.workoutDescription = workoutDescription;
            this.overallDuration = overallDuration;
            this.exercises = new ArrayList<>(exercises);
        }

        protected Workout(Parcel in) {
            workoutName = in.readString();
            workoutDescription = in.readString();
            overallDuration = in.readString();
            exercises = new ArrayList<>();
            in.readTypedList(exercises, Exercise.CREATOR);
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(workoutName);
            dest.writeString(workoutDescription);
            dest.writeString(overallDuration);
            dest.writeTypedList(exercises);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<Workout> CREATOR = new Creator<Workout>() {
            @Override
            public Workout createFromParcel(Parcel in) {
                return new Workout(in);
            }

            @Override
            public Workout[] newArray(int size) {
                return new Workout[size];
            }
        };

        public String getWorkoutName() {
            return workoutName;
        }

        public String getWorkoutDescription() {
            return workoutDescription;
        }

        public String getOverallDuration() {
            return overallDuration;
        }

        public List<Exercise> getExercises() {
            return exercises;
        }
    }

    public static class Exercise implements Parcelable {
        private final String exerciseName;
        private final String exerciseDuration;
        private boolean completed;

        public Exercise(String exerciseName, String exerciseDuration) {
            this.exerciseName = exerciseName;
            this.exerciseDuration = exerciseDuration;
            this.completed = false;
        }

        protected Exercise(Parcel in) {
            exerciseName = in.readString();
            exerciseDuration = in.readString();
            completed = in.readByte() != 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(exerciseName);
            dest.writeString(exerciseDuration);
            dest.writeByte((byte) (completed ? 1 : 0));
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<Exercise> CREATOR = new Creator<Exercise>() {
            @Override
            public Exercise createFromParcel(Parcel in) {
                return new Exercise(in);
            }

            @Override
            public Exercise[] newArray(int size) {
                return new Exercise[size];
            }
        };

        public String getExerciseName() {
            return exerciseName;
        }

        public String getExerciseDuration() {
            return exerciseDuration;
        }

        public boolean isCompleted() {
            return completed;
        }

        public void setCompleted(boolean completed) {
            this.completed = completed;
        }
    }

}