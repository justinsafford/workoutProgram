package customPackage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class)
public class ExerciseControllerTest {
    private ExerciseController exerciseController = new ExerciseController();

    @Test
    public void returnAvailableExercises(){



        ExerciseEntity actualExerciseEntity = exerciseController.getExercises();
        ExerciseEntity expectedExerciseEntity = new ExerciseEntity();
        expectedExerciseEntity.setExerciseName("Bicep Curl");
        expectedExerciseEntity.setMuscleGroup("Biceps");



        assertThat(actualExerciseEntity.getExerciseName(), is("Bicep Curl"));
        assertThat(actualExerciseEntity.getMuscleGroup(), is("Biceps"));



    }


}