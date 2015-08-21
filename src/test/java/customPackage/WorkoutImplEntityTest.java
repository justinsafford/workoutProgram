package customPackage;

import org.junit.Test;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class WorkoutImplEntityTest {


    @Test
    public void addExerciseToWorkout(){
        ExerciseEntity exerciseEntity = new ExerciseEntity();
        exerciseEntity.setExerciseName("blah");
        exerciseEntity.setMuscleGroup("blah");

        WorkoutImpl workoutImpl = new WorkoutImpl();

        workoutImpl.addExerciseToWorkout(exerciseEntity);

        assertNotNull(workoutImpl);
        assertThat(workoutImpl.exerciseEntityList.size(), is(1));
        assertTrue(workoutImpl.exerciseEntityList.contains(exerciseEntity));
    }

    @Test
    public void addMultipleExercisesToWorkout(){
        ExerciseEntity exerciseEntity = new ExerciseEntity();
        exerciseEntity.setExerciseName("exercise-name");
        exerciseEntity.setMuscleGroup("muscle-type");

        ExerciseEntity exerciseEntity2 = new ExerciseEntity();
        exerciseEntity2.setExerciseName("exercise2-name");
        exerciseEntity2.setMuscleGroup("muscle2-type");

        WorkoutImpl workoutImpl = new WorkoutImpl();

        workoutImpl.addExerciseToWorkout(exerciseEntity);
        workoutImpl.addExerciseToWorkout(exerciseEntity2);

        assertThat(workoutImpl.exerciseEntityList, hasSize(2));
    }

    @Test
    public void addRepsToExerciseInWorkout(){
        ExerciseEntity exerciseEntity = new ExerciseEntity();
        exerciseEntity.setExerciseName("exercise-name");
        exerciseEntity.setMuscleGroup("muscle-type");

        exerciseEntity.setReps(10);

        WorkoutImpl workoutImpl = new WorkoutImpl();

        workoutImpl.addExerciseToWorkout(exerciseEntity);

        assertThat(workoutImpl.exerciseEntityList, hasSize(1));
        assertThat(workoutImpl.exerciseEntityList, contains(exerciseEntity));
        assertThat(workoutImpl.exerciseEntityList.get(0).getExerciseName(), is("exercise-name"));
        assertThat(workoutImpl.exerciseEntityList.get(0).getMuscleGroup(), is("muscle-type"));
        assertThat(workoutImpl.exerciseEntityList.get(0).getReps(), is(10));
    }
}
