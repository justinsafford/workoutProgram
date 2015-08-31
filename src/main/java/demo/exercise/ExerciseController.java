package demo.exercise;

import demo.data.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExerciseController {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @RequestMapping(
            value = "/exercises",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<Exercise> getExercises() {
        return exerciseRepository.findAll();
    }

    public Exercise addNewExercise(Exercise exercise){
        return exerciseRepository.save(exercise);
    }

}
