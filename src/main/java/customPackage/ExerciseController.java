package customPackage;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExerciseController {



    @RequestMapping(
            value = "/exercises",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ExerciseEntity getExercises() {
        ExerciseEntity exerciseEntity = new ExerciseEntity();
        exerciseEntity.setExerciseName("Bicep Curl");
        exerciseEntity.setMuscleGroup("Biceps");

        return exerciseEntity;
    }


}
