package customPackage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class WorkoutController {

    @Autowired
    private WorkoutService workoutService;

    @RequestMapping(
            value = "/workout/{workoutId}/data",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.ACCEPTED)
    public WorkoutResponse submitWorkout(
            @PathVariable String workoutId,
            @RequestBody WorkoutRequest workoutRequest) {


        WorkoutResponse workoutResponse = workoutService.validate(workoutId);

        return workoutResponse;
    }


}
