package demo.exercise;

import demo.Application;
import demo.data.ExerciseRepository;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ExerciseTest {
    @Mock
    ExerciseRepository exerciseRepository;

    @InjectMocks
    ExerciseController exerciseController;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void saveExercise() {
        Exercise exercise = new Exercise();

        Exercise expectedExercise = new Exercise();
        when(exerciseRepository.save(exercise)).thenReturn(expectedExercise);

        Exercise actualExercise = exerciseController.addNewExercise(exercise);

        assertThat(actualExercise, is(expectedExercise));
        verify(exerciseRepository, times(1)).save(exercise);
    }

    @Test
    public void returnAvailableExercises() {
        List<Exercise> expectedData = Collections.singletonList(new Exercise());
        when(exerciseRepository.findAll()).thenReturn(expectedData);

        List<Exercise> actualData = exerciseController.getExercises();

        MatcherAssert.assertThat(actualData, Matchers.is(expectedData));

//        Exercise exercise = new Exercise();
//        exercise.setExerciseName("Chest Press");
//        exercise.setMuscleGroup("Chest");
//        exercise.setReps(10);
//
//        Exercise exercise2 = new Exercise();
//        exercise2.setExerciseName("Bicep Curl");
//        exercise2.setMuscleGroup("Biceps");
//        exercise2.setReps(12);
//
//        exerciseRepository.save(exercise);
//        exerciseRepository.save(exercise2);
//
//
//        ClassPathResource classPathResource = new ClassPathResource("/responses/getExercises.json");
//        String expectedBody = new String(Files.readAllBytes(Paths.get(classPathResource.getURI())));
//
//        mockMvc.perform(get("/exercises"))
//                .andExpect(status().isOk())
//                .andExpect(content().json(expectedBody));
//
//
//        List<Exercise> retrievedExerciseList = exerciseRepository.findAll();
//
//        MatcherAssert.assertThat(retrievedExerciseList.get(0).getExerciseName(), Matchers.is("Chest Press"));
//        MatcherAssert.assertThat(retrievedExerciseList.get(0).getMuscleGroup(), Matchers.is("Chest"));
//        MatcherAssert.assertThat(retrievedExerciseList.get(0).getReps(), Matchers.is(10));
//
//        MatcherAssert.assertThat(retrievedExerciseList.get(1).getExerciseName(), Matchers.is("Bicep Curl"));
//        MatcherAssert.assertThat(retrievedExerciseList.get(1).getMuscleGroup(), Matchers.is("Biceps"));
//        MatcherAssert.assertThat(retrievedExerciseList.get(1).getReps(), Matchers.is(12));
//    }

    }
}