package edu.utsa.cs3773.thebestyou.model;

import java.util.List;

public class ChallengeDetail {
    private String title;
    private String description;
    private String imageResId;
    private List<Workout> workouts;

    public ChallengeDetail(String title, String description, String imageResId, List<Workout> workouts) {
        this.title = title;
        this.description = description;
        this.imageResId = imageResId;
        this.workouts = workouts;
    }

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

    public List<Workout> getWorkouts() {
        return workouts;
    }

    public static class Workout {
        private String name;
        private String duration;
        private List<Exercise> exercises;

        public Workout(String name, String duration, List<Exercise> exercises) {
            this.name = name;
            this.duration = duration;
            this.exercises = exercises;
        }

        // Getters
        public String getName() {
            return name;
        }

        public String getDuration() {
            return duration;
        }

        public List<Exercise> getExercises() {
            return exercises;
        }

        public static class Exercise {
            private String name;
            private String description;

            public Exercise(String name, String description) {
                this.name = name;
                this.description = description;
            }

            // Getters
            public String getName() {
                return name;
            }

            public String getDescription() {
                return description;
            }
        }
    }
}