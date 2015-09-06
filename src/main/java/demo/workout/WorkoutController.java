package demo.workout;

import demo.data.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class WorkoutController {

    WorkoutRepository workoutRepository;

    @Autowired
    public WorkoutController(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    @RequestMapping(
            value = "/workouts",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public WorkoutEntity addNewWorkout(@RequestBody WorkoutRequest workoutRequest) {
        WorkoutEntity workoutEntity = new WorkoutEntity();
        workoutEntity.setDayWorkoutOccurred(workoutRequest.getDayWorkoutOccurred());
        workoutEntity.setExerciseSets(workoutRequest.getExerciseSets());

        workoutRepository.save(workoutEntity);

        return workoutEntity;

    }
}
















//    @RequestMapping(
//            value = "/workouts",
//            method = RequestMethod.GET,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public List<Exercise> getExercises() {
//        return exerciseRepository.findAll();
//    }
