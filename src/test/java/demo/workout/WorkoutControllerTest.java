package demo.workout;

import demo.Application;
import demo.data.WorkoutRepository;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.boot.test.SpringApplicationConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.*;

@SpringApplicationConfiguration(classes = Application.class)
public class WorkoutControllerTest {
    @Mock
    private WorkoutRepository workoutRepository;

    @InjectMocks
    private WorkoutController workoutController;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void addNewWorkoutPersistsWorkout() {
        WorkoutRequest workoutRequest = new WorkoutRequest();
        workoutRequest.setDayWorkoutOccurred("Wednesday");

        ExerciseSetsReps exerciseSetsReps = new ExerciseSetsReps();
        exerciseSetsReps.setExerciseName("exercise");
        exerciseSetsReps.setMuscleGroup("muscle");
        exerciseSetsReps.setReps(new String[]{"1", "2", "3", "4"});

        List<ExerciseSetsReps> exerciseSetsRepsList = new ArrayList<>();
        exerciseSetsRepsList.add(exerciseSetsReps);
        workoutRequest.setExerciseSetsReps(exerciseSetsRepsList);

        WorkoutEntity newWorkout = new WorkoutEntity();
        WorkoutEntity expectedWorkout = new WorkoutEntity();
        when(workoutRepository.save(newWorkout)).thenReturn(expectedWorkout);

        WorkoutEntity actualWorkout = workoutController.addNewWorkout(workoutRequest);

//        TODO:Make this tested
//        assertThat(actualWorkout, is(expectedWorkout));
        verify(workoutRepository, times(1)).save(isA(WorkoutEntity.class));
        verifyNoMoreInteractions(workoutRepository);
    }



//    @Test
//    public void returnAvailableExercises() {
//        List<ExerciseEntity> expectedData = Collections.singletonList(new ExerciseEntity());
//        when(exerciseRepository.findAll()).thenReturn(expectedData);
//
//        List<ExerciseEntity> actualData = exerciseController.getExercises();
//
//        assertThat(actualData, is(expectedData));
//    }
}