package demo.workout;

import java.util.ArrayList;
import java.util.List;

public class WorkoutRequest {
    private String dayWorkoutOccurred;
    private List<ExerciseSet> exerciseSets = new ArrayList<>();

    public void setDayWorkoutOccurred(String dayWorkoutOccurred) {
        this.dayWorkoutOccurred = dayWorkoutOccurred;
    }

    public String getDayWorkoutOccurred() {
        return dayWorkoutOccurred;
    }

    public List<ExerciseSet> getExerciseSets() {
        return exerciseSets;
    }

    public void setExerciseSets(List<ExerciseSet> exerciseSets) {
        this.exerciseSets = exerciseSets;
    }
}
