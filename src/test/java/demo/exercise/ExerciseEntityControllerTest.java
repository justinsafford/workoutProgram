package demo.exercise;

import demo.Application;
import demo.data.ExerciseRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.boot.test.SpringApplicationConfiguration;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.Is.isA;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@SpringApplicationConfiguration(classes = Application.class)
public class ExerciseEntityControllerTest {
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
        ExerciseEntity exerciseEntity = new ExerciseEntity();
        exerciseEntity.setExerciseName("Shoulder Press");
        exerciseEntity.setMuscleGroup("Shoulder");

        ExerciseEntity expectedExerciseEntity = new ExerciseEntity();
        when(exerciseRepository.save(exerciseEntity)).thenReturn(expectedExerciseEntity);

        ExerciseRequest exerciseRequest = new ExerciseRequest();
//        exerciseRequest.setExerciseName("exerciseEntity");
//        exerciseRequest.setMuscleGroup("muscle");
        ExerciseEntity actualExerciseEntity = exerciseController.addNewExercise(exerciseRequest);
//TODO:Figure out how to test this controller
// assertThat(actualExerciseEntity, is(expectedExerciseEntity))‰;
        verify(exerciseRepository, times(1)).save(Matchers.isA(ExerciseEntity.class));
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
    @Test
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
        List<ExerciseEntity> expectedData = Collections.singletonList(new ExerciseEntity());
        when(exerciseRepository.findAll()).thenReturn(expectedData);

        List<ExerciseEntity> actualData = exerciseController.getExercises();

        assertThat(actualData, is(expectedData));
    }
}