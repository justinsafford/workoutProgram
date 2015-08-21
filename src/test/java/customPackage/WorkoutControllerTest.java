package customPackage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class)
public class WorkoutControllerTest {

    @Autowired
    private WorkoutController workoutController;

    @Test
    public void workoutController_submitAWorkout(){
        ExerciseEntity exerciseEntity = new ExerciseEntity();
        exerciseEntity.setExerciseName("exercise-name");
        exerciseEntity.setMuscleGroup("muscle-type");
        exerciseEntity.setReps(10);

        Workout workout = new Workout();
        workout.addExerciseToWorkout(exerciseEntity);


        WorkoutRequest workoutRequest = new WorkoutRequest();
        workoutRequest.setWorkout(workout);

        String workoutId = "workoutID";
        WorkoutResponse actualWorkoutResponse = workoutController.submitWorkout(workoutId, workoutRequest);


        assertThat(workoutRequest, is(WorkoutRequest.class));
        assertThat(actualWorkoutResponse, is(WorkoutResponse.class));
        assertThat(actualWorkoutResponse.isGood, is(true));
    }


    @Test
    public void workoutController_submitANullWorkoutId_returnsNotGood(){
        ExerciseEntity exerciseEntity = new ExerciseEntity();
        exerciseEntity.setExerciseName("exercise-name");
        exerciseEntity.setMuscleGroup("muscle-type");
        exerciseEntity.setReps(10);

        Workout workout = new Workout();
        workout.addExerciseToWorkout(exerciseEntity);

        WorkoutRequest workoutRequest = new WorkoutRequest();
        workoutRequest.setWorkout(workout);


        WorkoutResponse actualWorkoutResponse = workoutController.submitWorkout(null, workoutRequest);


        assertThat(actualWorkoutResponse.isGood, is(false));
    }


}