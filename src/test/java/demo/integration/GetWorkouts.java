package demo.integration;

import demo.Application;
import demo.data.WorkoutRepository;
import demo.workout.ExerciseSet;
import demo.workout.WorkoutEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class GetWorkouts {

    @Autowired
    WorkoutRepository workoutRepository;

    @Autowired
    WebApplicationContext webApplicationContext;

    MockMvc mockMvc;

    @Before
    public void setupMock() {
        mockMvc = webAppContextSetup(webApplicationContext)
                .defaultRequest(get("/")
                        .accept(MediaType.APPLICATION_JSON))
                .alwaysExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .build();
    }

    @Before
    public void clearDb() {
        workoutRepository.deleteAll();
    }

    @Test
    public void returnAllAvailableWorkout_Integration() throws Exception {
        WorkoutEntity workoutEntity = new WorkoutEntity();
        workoutEntity.setDayWorkoutOccurred("Thursday");

        ExerciseSet exerciseSet1 = new ExerciseSet();
        exerciseSet1.setExerciseName("Incline Shoulder Press");
        exerciseSet1.setMuscleGroup("Shoulder");
        exerciseSet1.setReps(new String[]{"10", "9", "8", "8"});

        ExerciseSet exerciseSet2 = new ExerciseSet();
        exerciseSet2.setExerciseName("Incline Bench Press");
        exerciseSet2.setMuscleGroup("Chest");
        exerciseSet2.setReps(new String[]{"20", "12", "13", "14", "15"});

        List<ExerciseSet> exerciseSets = new ArrayList<>();
        exerciseSets.add(exerciseSet1);
        exerciseSets.add(exerciseSet2);
        workoutEntity.setExerciseSets(exerciseSets);

        workoutRepository.save(workoutEntity);

        ClassPathResource classPathResource = new ClassPathResource("/responses/getWorkouts.json");
        String expectedBody = new String(Files.readAllBytes(Paths.get(classPathResource.getURI())));

        mockMvc.perform(get("/workouts"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedBody));
    }
}
