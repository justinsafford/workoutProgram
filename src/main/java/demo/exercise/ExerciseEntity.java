package demo.exercise;

import org.springframework.data.annotation.Id;

public class ExerciseEntity {

    @Id
    private String id;

    private String exerciseName;
    private String muscleGroup;


    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getMuscleGroup() {
        return muscleGroup;
    }

    public void setMuscleGroup(String muscleGroup) {
        this.muscleGroup = muscleGroup;
    }
}

