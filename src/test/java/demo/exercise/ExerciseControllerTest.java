package demo.exercise;

import demo.data.ExerciseRepository;
import org.junit.Rule;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class ExerciseControllerTest {

    @Mock
    ExerciseRepository exerciseRepository;

    @InjectMocks
    ExerciseController exerciseController;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();



}