package customPackage;

public class ExerciseEntity {
    private String exerciseName;
    private String muscleGroup;
    private int reps;


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

    public void setReps(int reps) {
        this.reps = reps;
    }

    public Integer getReps() {
        return reps;
    }

}

