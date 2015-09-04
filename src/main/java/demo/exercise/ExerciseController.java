package demo.exercise;

import demo.data.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ExerciseController {
    private ExerciseRepository exerciseRepository;

    @Autowired
    public ExerciseController(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @RequestMapping(
            value = "/exercises",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Exercise> getExercises() {
        return exerciseRepository.findAll();
    }

    @RequestMapping(
            value = "/exercises",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Exercise addNewExercise(@RequestBody ExerciseRequest exerciseRequest) {

        if(exerciseRequest.getExerciseName() == ""){
            throw new BadRequestException("You must send Exercise Name");
        }

        if(exerciseRequest.getMuscleGroup() == ""){
            throw new BadRequestException("You must send Muscle Group");
        }

        Exercise exercise = new Exercise();
        exercise.setExerciseName(exerciseRequest.getExerciseName());
        exercise.setMuscleGroup(exerciseRequest.getMuscleGroup());
        exerciseRepository.save(exercise);

        return exercise;
    }
}
