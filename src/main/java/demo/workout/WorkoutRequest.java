package demo.workout;

import java.util.ArrayList;
import java.util.List;

public class WorkoutRequest {
    private String dayWorkoutOccurred;
    private List<ExerciseSetsReps> exerciseSetsReps = new ArrayList<>();

    public void setDayWorkoutOccurred(String dayWorkoutOccurred) {
        this.dayWorkoutOccurred = dayWorkoutOccurred;
    }

    public String getDayWorkoutOccurred() {
        return dayWorkoutOccurred;
    }

    public List<ExerciseSetsReps> getExerciseSetsReps() {
        return exerciseSetsReps;
    }

    public void setExerciseSetsReps(List<ExerciseSetsReps> exerciseSetsReps) {
        this.exerciseSetsReps = exerciseSetsReps;
    }
}
