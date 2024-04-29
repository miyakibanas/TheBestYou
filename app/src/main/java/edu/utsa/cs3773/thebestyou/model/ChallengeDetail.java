// ChallengeDetail.java
package edu.utsa.cs3773.thebestyou.model;

import java.util.ArrayList;
import java.util.List;

public class ChallengeDetail {
    private final String challengeName;
    private final String goalName;
    private final Level level;

    public ChallengeDetail(String challengeName, String goalName, Level level) {
        this.challengeName = challengeName;
        this.goalName = goalName;
        this.level = level;
    }

    public String getChallengeName() {
        return challengeName;
    }

    public String getGoalName() {
        return goalName;
    }

    public Level getLevel() {
        return level;
    }

    public static class Level {
        private final String levelName;
        private final List<Week> weeks;

        public Level(String levelName, List<Week> weeks) {
            this.levelName = levelName;
            this.weeks = weeks;
        }

        public String getLevelName() {
            return levelName;
        }

        public List<Week> getWeeks() {
            return weeks;
        }
    }

    public static class Week {
        private final int weekNumber;
        private final Workout workout;

        public Week(int weekNumber, Workout workout) {
            this.weekNumber = weekNumber;
            this.workout = workout;
        }

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

    public static class Workout {
        private final String workoutName;
        private final String workoutDescription;
        private final String overallDuration;
        private final List<Exercise> exercises;

        public Workout(String workoutName, String workoutDescription, String overallDuration, List<Exercise> exercises) {
            this.workoutName = workoutName;
            this.workoutDescription = workoutDescription;
            this.overallDuration = overallDuration;
            this.exercises = exercises;
        }

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

    public static class Exercise {
        private final String exerciseName;
        private final String exerciseDuration;
        private boolean completed; // New field to track completion status

        public Exercise(String exerciseName, String exerciseDuration) {
            this.exerciseName = exerciseName;
            this.exerciseDuration = exerciseDuration;
            this.completed = false; // By default, exercise is not completed

        }

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