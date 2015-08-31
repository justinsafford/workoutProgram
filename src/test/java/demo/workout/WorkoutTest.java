package demo.workout;

import demo.Application;
import demo.data.WorkoutRepository;
import demo.exercise.Exercise;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.DayOfWeek;
import java.util.List;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.util.MatcherAssertionErrors.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class WorkoutTest {
    @Autowired
    WorkoutRepository workoutRepository;

    Workout workout = new Workout();

    @Test
    public void addExerciseToWorkout() {
        Exercise exercise = new Exercise();
        exercise.setExerciseName("blah");
        exercise.setMuscleGroup("blah");

        workout.addExerciseToWorkout(exercise);

        assertNotNull(workout);
        assertThat(workout.getExerciseList().size(), is(1));
        assertTrue(workout.getExerciseList().contains(exercise));
    }

    @Test
    public void addMultipleExercisesToWorkout() {
        Exercise exercise = new Exercise();
        exercise.setExerciseName("exercise-name");
        exercise.setMuscleGroup("muscle-type");

        Exercise exercise2 = new Exercise();
        exercise2.setExerciseName("exercise2-name");
        exercise2.setMuscleGroup("muscle2-type");

        workout.addExerciseToWorkout(exercise);
        workout.addExerciseToWorkout(exercise2);

        assertThat(workout.getExerciseList(), hasSize(2));
    }

    @Test
    public void addRepsToExerciseInWorkout() {
        Exercise exercise = new Exercise();
        exercise.setExerciseName("exercise-name");
        exercise.setMuscleGroup("muscle-type");

        exercise.setReps(10);

        workout.addExerciseToWorkout(exercise);

        assertThat(workout.getExerciseList(), hasSize(1));
        assertThat(workout.getExerciseList(), contains(exercise));
        assertThat(workout.getExerciseList().get(0).getExerciseName(), is("exercise-name"));
        assertThat(workout.getExerciseList().get(0).getMuscleGroup(), is("muscle-type"));
        assertThat(workout.getExerciseList().get(0).getReps(), is(10));
    }

    @Test
    public void addingMultipleRepsToExerciseAddsNewExerciseWithSameNameInWorkout() {
        Exercise exercise = new Exercise();
        exercise.setExerciseName("exercise-name");
        exercise.setMuscleGroup("muscle-type");
        exercise.setReps(10);

        Exercise anotherExercise = new Exercise();
        anotherExercise.setExerciseName("exercise-name2");
        anotherExercise.setMuscleGroup("muscle-type2");
        anotherExercise.setReps(20);

        workout.addExerciseToWorkout(exercise);
        workout.addExerciseToWorkout(anotherExercise);

        assertThat(workout.getExerciseList(), hasSize(2));
        assertThat(workout.getExerciseList(), contains(exercise, anotherExercise));
        assertThat(workout.getExerciseList().get(0).getExerciseName(), is("exercise-name"));
        assertThat(workout.getExerciseList().get(0).getMuscleGroup(), is("muscle-type"));
        assertThat(workout.getExerciseList().get(0).getReps(), is(10));

        assertThat(workout.getExerciseList().get(1).getExerciseName(), is("exercise-name2"));
        assertThat(workout.getExerciseList().get(1).getMuscleGroup(), is("muscle-type2"));
        assertThat(workout.getExerciseList().get(1).getReps(), is(20));
    }

    @Test
    public void setDayToWorkout() {
        workout.setDayOfWeek(String.valueOf(DayOfWeek.MONDAY));

        assertThat(workout.getDayOfWeek(), Matchers.is("MONDAY"));
    }

    @Test
    public void retrieveMultipleExercisesFromWorkout() {
        Exercise exercise = new Exercise();
        exercise.setExerciseName("exercise-name1");
        exercise.setMuscleGroup("muscle-type1");
        exercise.setReps(10);

        Exercise anotherExercise = new Exercise();
        anotherExercise.setExerciseName("exercise-name1");
        anotherExercise.setMuscleGroup("muscle-type1");
        anotherExercise.setReps(20);

        Exercise anotherExercise2 = new Exercise();
        anotherExercise2.setExerciseName("exercise-name2");
        anotherExercise2.setMuscleGroup("muscle-type2");
        anotherExercise2.setReps(30);

        workout.addExerciseToWorkout(exercise);
        workout.addExerciseToWorkout(anotherExercise);
        workout.addExerciseToWorkout(anotherExercise2);

        List<Exercise> list = workout.retrieveExercisesFromWorkout();
        assertNotNull(list);

        assertThat(workout.getExerciseList(), hasSize(3));
    }

    @Test
    public void saveWorkout() {
        Workout workout1 = new Workout();
        workout1.setDayOfWeek("Monday");

        Exercise exercise1 = new Exercise();
        exercise1.setExerciseName("Bench Press");
        exercise1.setMuscleGroup("Chest");
        exercise1.setReps(10);

        Exercise exercise2 = new Exercise();
        exercise2.setExerciseName("Row");
        exercise2.setMuscleGroup("Back");
        exercise2.setReps(20);

        workout1.addExerciseToWorkout(exercise1);
        workout1.addExerciseToWorkout(exercise2);

        workoutRepository.save(workout1);
    }
}
