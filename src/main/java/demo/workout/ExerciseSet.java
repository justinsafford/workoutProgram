package demo.workout;

public class ExerciseSet {
    private String exerciseName;
    private String muscleGroup;
    private String[] reps;

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getExerciseName() {
        return exerciseName;
    }


    public void setMuscleGroup(String muscleGroup) {
        this.muscleGroup = muscleGroup;
    }

    public String getMuscleGroup() {
        return muscleGroup;
    }

    public void setReps(String[] reps) {
        this.reps = reps;
    }

    public String[] getReps() {
        return reps;
    }
}
