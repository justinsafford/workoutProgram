package demo.workout;

import org.springframework.data.annotation.Id;

import java.util.List;

public class WorkoutEntity {

    @Id
    private String Id;

    private String dayWorkoutOccurred;
    private List<ExerciseSetsReps> exerciseSetsReps;


    public String getDayWorkoutOccurred() {
        return dayWorkoutOccurred;
    }

    public void setDayWorkoutOccurred(String dayWorkoutOccurred) {
        this.dayWorkoutOccurred = dayWorkoutOccurred;
    }

    public List<ExerciseSetsReps> getExerciseSetsReps() {
        return exerciseSetsReps;
    }

    public void setExerciseSetsReps(List<ExerciseSetsReps> exerciseSetsReps) {
        this.exerciseSetsReps = exerciseSetsReps;
    }
}
