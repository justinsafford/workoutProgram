package demo.integration;

import demo.Application;
import demo.data.WorkoutRepository;
import demo.workout.WorkoutController;
import demo.workout.WorkoutEntity;
import org.hamcrest.Matchers;
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

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class addWorkout {

    @Autowired
    private WorkoutRepository workoutRepository;

    @Autowired
    private WorkoutController workoutController;

    MockMvc mockMvc;

    @Before
    public void setupMock() {
//        TODO:put standalones in unit tests and WebAPpContext mocks in Integration
        mockMvc = standaloneSetup(workoutController)
                .build();
    }

    @Before
    public void clearDb() {
        workoutRepository.deleteAll();
    }

    @Test
    public void addNewWorkout() throws Exception {
        ClassPathResource classPathResource = new ClassPathResource("/responses/addWorkout.json");
        String expectedBody = new String(Files.readAllBytes(Paths.get(classPathResource.getURI())));

        mockMvc.perform(post("/workouts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(expectedBody)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        List<WorkoutEntity> workoutList = workoutRepository.findAll();
        assertThat(workoutList, hasSize(1));
        WorkoutEntity returnedWorkoutEntity = workoutList.get(0);

        assertThat(returnedWorkoutEntity.getDayWorkoutOccurred(), is("Tuesday"));
        assertThat(returnedWorkoutEntity.getExerciseSets().size(), Matchers.is(1));
        assertThat(returnedWorkoutEntity.getExerciseSets().get(0).getExerciseName(), is("Shoulder Press"));
        assertThat(returnedWorkoutEntity.getExerciseSets().get(0).getMuscleGroup(), is("Shoulder"));
        assertThat(returnedWorkoutEntity.getExerciseSets().get(0).getReps().length, Matchers.is(4));
        //dig into reps
        assertThat(returnedWorkoutEntity.getExerciseSets().get(0).getReps()[0], is("1"));
        assertThat(returnedWorkoutEntity.getExerciseSets().get(0).getReps()[1], is("2"));
        assertThat(returnedWorkoutEntity.getExerciseSets().get(0).getReps()[2], is("3"));
        assertThat(returnedWorkoutEntity.getExerciseSets().get(0).getReps()[3], is("4"));
    }

    @Test
    public void addMultipleNewWorkouts() throws Exception {
        ClassPathResource classPathResource = new ClassPathResource("/responses/addWorkouts.json");
        String expectedBody = new String(Files.readAllBytes(Paths.get(classPathResource.getURI())));

        mockMvc.perform(post("/workouts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(expectedBody)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        List<WorkoutEntity> workoutList = workoutRepository.findAll();
        assertThat(workoutList, hasSize(1));
        WorkoutEntity returnedWorkoutEntity = workoutList.get(0);

        assertThat(returnedWorkoutEntity.getDayWorkoutOccurred(), is("Tuesday"));
        assertThat(returnedWorkoutEntity.getExerciseSets().size(), Matchers.is(2));
        assertThat(returnedWorkoutEntity.getExerciseSets().get(0).getExerciseName(), is("Shoulder Press"));
        assertThat(returnedWorkoutEntity.getExerciseSets().get(0).getMuscleGroup(), is("Shoulder"));
        assertThat(returnedWorkoutEntity.getExerciseSets().get(0).getReps().length, Matchers.is(4));

        assertThat(returnedWorkoutEntity.getExerciseSets().get(1).getExerciseName(), is("Bench Press"));
        assertThat(returnedWorkoutEntity.getExerciseSets().get(1).getMuscleGroup(), is("Chest"));
        assertThat(returnedWorkoutEntity.getExerciseSets().get(1).getReps().length, Matchers.is(5));
    }
}