package demo.exercise;

import demo.Application;
import demo.data.ExerciseRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ExerciseControllerTest {
    @Mock
    ExerciseRepository exerciseRepository;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @InjectMocks
    ExerciseController exerciseController;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void addNewExercise() {
//        Exercise exercise = new Exercise();
//        exercise.setExerciseName("Shoulder Press");
//        exercise.setMuscleGroup("Shoulder");
//
//        Exercise expectedExercise = new Exercise();
//        when(exerciseRepository.save(exercise)).thenReturn(expectedExercise);
//
//        ExerciseRequest exerciseRequest = new ExerciseRequest();
//        Exercise actualExercise = exerciseController.addNewExercise(exerciseRequest);
//
////TODO:Figure out how to test this controller
//// assertThat(actualExercise, is(expectedExercise));
//        verify(exerciseRepository, times(1)).save((Iterable<Exercise>) isA(Exercise.class));
    }

    @Test
    public void addNewExerciseWithNoExerciseName() throws BadRequestException {
//        expectedException.expectCause(isA(BadRequestException.class));
//        expectedException.expectMessage("You must send Exercise Name");
//
//        ExerciseRequest exerciseRequest = new ExerciseRequest();
//        exerciseRequest.setExerciseName("");
//        exerciseRequest.setMuscleGroup("Muscle");
//        Exercise actualExercise = exerciseController.addNewExercise(exerciseRequest);

    }

    @Test
    public void returnAvailableExercises() {
//        List<Exercise> expectedData = Collections.singletonList(new Exercise());
//        when(exerciseRepository.findAll()).thenReturn(expectedData);
//
//        List<Exercise> actualData = exerciseController.getExercises();
//
//        assertThat(actualData, is(expectedData));
    }
}