package demo.workout;

import org.springframework.data.annotation.Id;

import java.util.List;

public class WorkoutEntity {

    @Id
    private String Id;
    private String dayWorkoutOccurred;
    private List<ExerciseSet> exerciseSets;


    public String getDayWorkoutOccurred() {
        return dayWorkoutOccurred;
    }

    public void setDayWorkoutOccurred(String dayWorkoutOccurred) {
        this.dayWorkoutOccurred = dayWorkoutOccurred;
    }

    public List<ExerciseSet> getExerciseSets() {
        return exerciseSets;
    }

    public void setExerciseSets(List<ExerciseSet> exerciseSets) {
        this.exerciseSets = exerciseSets;
    }
}
