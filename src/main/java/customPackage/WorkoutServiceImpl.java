package customPackage;

import org.springframework.stereotype.Component;

@Component
public class WorkoutServiceImpl implements WorkoutService{
    public WorkoutResponse validate(String workoutId){

        WorkoutResponse workoutResponse = new WorkoutResponse();

        if(workoutId == null) {
            workoutResponse.setIsGood(false);
            return workoutResponse;
        }
        workoutResponse.setIsGood(true);
        return workoutResponse;
    }
}
