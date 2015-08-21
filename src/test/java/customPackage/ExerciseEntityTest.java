package customPackage;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ExerciseEntityTest {

    @Test
    public void setNameAndMuscleGroup(){
        ExerciseEntity exerciseEntity = new ExerciseEntity();
        exerciseEntity.setExerciseName("Flat Bench");
        exerciseEntity.setMuscleGroup("Chest");

        assertThat(exerciseEntity.getExerciseName(), is("Flat Bench"));
        assertThat(exerciseEntity.getMuscleGroup(), is("Chest"));

    }

}