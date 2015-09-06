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

        ExerciseSet exerciseSet = new ExerciseSet();
        exerciseSet.setExerciseName("exercise");
        exerciseSet.setMuscleGroup("muscle");
        exerciseSet.setReps(new String[]{"1", "2", "3", "4"});

        List<ExerciseSet> exerciseSetList = new ArrayList<>();
        exerciseSetList.add(exerciseSet);
        workoutRequest.setExerciseSets(exerciseSetList);

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
//        List<Exercise> expectedData = Collections.singletonList(new Exercise());
//        when(exerciseRepository.findAll()).thenReturn(expectedData);
//
//        List<Exercise> actualData = exerciseController.getExercises();
//
//        assertThat(actualData, is(expectedData));
//    }
}