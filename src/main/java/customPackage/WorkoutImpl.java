package customPackage;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WorkoutImpl implements Workout {
    List<ExerciseEntity> exerciseEntityList = new ArrayList<ExerciseEntity>();


    public List<ExerciseEntity> addExerciseToWorkout(ExerciseEntity exerciseEntity) {
        exerciseEntityList.add(exerciseEntity);
        return exerciseEntityList;
    }
}
