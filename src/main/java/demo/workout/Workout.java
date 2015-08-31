package demo.workout;

import demo.exercise.Exercise;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

public class Workout {

    @Id
    private String Id;

    private List<Exercise> exerciseList = new ArrayList<>();
    private String dayOfWeek;

    public List<Exercise> getExerciseList() {
        return exerciseList;
    }

    public void setExerciseList(List<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public void addExerciseToWorkout(Exercise exercise) {
        exerciseList.add(exercise);
    }

    public List<Exercise> retrieveExercisesFromWorkout() {
        return exerciseList;
    }
}
