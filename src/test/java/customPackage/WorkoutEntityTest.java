package customPackage;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=Config.class)
public class WorkoutEntityTest {

    @Autowired
    Workout workout;

    @Before
    public void purgeWorkoutList(){
        workout.exerciseEntityList.clear();
    }

    @Test
    public void addExerciseToWorkout(){
        ExerciseEntity exerciseEntity = new ExerciseEntity();
        exerciseEntity.setExerciseName("blah");
        exerciseEntity.setMuscleGroup("blah");

        workout.addExerciseToWorkout(exerciseEntity);

        assertNotNull(workout);
        assertThat(workout.exerciseEntityList.size(), is(1));
        assertTrue(workout.exerciseEntityList.contains(exerciseEntity));
    }

    @Test
    public void addMultipleExercisesToWorkout(){
        ExerciseEntity exerciseEntity = new ExerciseEntity();
        exerciseEntity.setExerciseName("exercise-name");
        exerciseEntity.setMuscleGroup("muscle-type");

        ExerciseEntity exerciseEntity2 = new ExerciseEntity();
        exerciseEntity2.setExerciseName("exercise2-name");
        exerciseEntity2.setMuscleGroup("muscle2-type");


        workout.addExerciseToWorkout(exerciseEntity);
        workout.addExerciseToWorkout(exerciseEntity2);

        assertThat(workout.exerciseEntityList, hasSize(2));
    }

    @Test
    public void addRepsToExerciseInWorkout(){
        ExerciseEntity exerciseEntity = new ExerciseEntity();
        exerciseEntity.setExerciseName("exercise-name");
        exerciseEntity.setMuscleGroup("muscle-type");

        exerciseEntity.setReps(10);

        Workout workout = new Workout();

        workout.addExerciseToWorkout(exerciseEntity);

        assertThat(workout.exerciseEntityList, hasSize(1));
        assertThat(workout.exerciseEntityList, contains(exerciseEntity));
        assertThat(workout.exerciseEntityList.get(0).getExerciseName(), is("exercise-name"));
        assertThat(workout.exerciseEntityList.get(0).getMuscleGroup(), is("muscle-type"));
        assertThat(workout.exerciseEntityList.get(0).getReps(), is(10));
    }
}
