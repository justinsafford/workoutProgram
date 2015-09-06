package demo.data;

import demo.exercise.ExerciseEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExerciseRepository extends MongoRepository<ExerciseEntity, String> {
}
