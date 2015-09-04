package demo.exercise;

import demo.Application;
import demo.data.ExerciseRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.Is.isA;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

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
        Exercise exercise = new Exercise();
        exercise.setExerciseName("Shoulder Press");
        exercise.setMuscleGroup("Shoulder");

        Exercise expectedExercise = new Exercise();
        when(exerciseRepository.save(exercise)).thenReturn(expectedExercise);

        ExerciseRequest exerciseRequest = new ExerciseRequest();
//        exerciseRequest.setExerciseName("exercise");
//        exerciseRequest.setMuscleGroup("muscle");
        Exercise actualExercise = exerciseController.addNewExercise(exerciseRequest);

//TODO:Figure out how to test this controller
// assertThat(actualExercise, is(expectedExercise))‰;
        verify(exerciseRepository, times(1)).save(Matchers.isA(Exercise.class));
    }

    @Test
    public void addNewExerciseWithNoExerciseName_throwsBadRequestException() throws BadRequestException {
        expectedException.expect(isA(BadRequestException.class));
        expectedException.expectMessage("You must send Exercise Name");

        ExerciseRequest exerciseRequest = new ExerciseRequest();
        exerciseRequest.setExerciseName("");
        exerciseRequest.setMuscleGroup("Muscle");
        exerciseController.addNewExercise(exerciseRequest);

        verifyNoMoreInteractions(exerciseController, exerciseRepository);
    }

    //TODO: Look to implement SPRING's Error validation
    public void addNewExerciseWithNoMuscleGroup_throwsBadRequestException() throws BadRequestException {
        expectedException.expect(isA(BadRequestException.class));
        expectedException.expectMessage("You must send Muscle Group");

        ExerciseRequest exerciseRequest = new ExerciseRequest();
        exerciseRequest.setExerciseName("something");
        exerciseRequest.setMuscleGroup("");
        exerciseController.addNewExercise(exerciseRequest);
        verifyNoMoreInteractions(exerciseController, exerciseRepository);
    }

    @Test
    public void returnAvailableExercises() {
        List<Exercise> expectedData = Collections.singletonList(new Exercise());
        when(exerciseRepository.findAll()).thenReturn(expectedData);

        List<Exercise> actualData = exerciseController.getExercises();

        assertThat(actualData, is(expectedData));
    }
}