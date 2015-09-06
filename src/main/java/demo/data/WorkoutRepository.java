package demo.data;

import demo.workout.WorkoutEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WorkoutRepository extends MongoRepository<WorkoutEntity, String> {
}
