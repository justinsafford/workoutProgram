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
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ExerciseEntity addNewExercise(@RequestBody ExerciseRequest exerciseRequest) {

        if(exerciseRequest.getExerciseName() == ""){
            throw new BadRequestException("You must send Exercise Name");
        }

        if(exerciseRequest.getMuscleGroup() == ""){
            throw new BadRequestException("You must send Muscle Group");
        }

        ExerciseEntity exerciseEntity = new ExerciseEntity();
        exerciseEntity.setExerciseName(exerciseRequest.getExerciseName());
        exerciseEntity.setMuscleGroup(exerciseRequest.getMuscleGroup());
        exerciseRepository.save(exerciseEntity);

        return exerciseEntity;
    }

    @RequestMapping(
            value = "/exercises",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ExerciseEntity> getExercises() {
        return exerciseRepository.findAll();
    }
}
