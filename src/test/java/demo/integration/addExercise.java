package demo;

import demo.data.ExerciseRepository;
import demo.exercise.ExerciseEntity;
import demo.exercise.ExerciseController;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class addExercise {

    @Autowired
    ExerciseRepository exerciseRepository;

    @Autowired
    ExerciseController exerciseController;

    MockMvc mockMvc;

    @Before
    public void setupMock() {
        mockMvc = standaloneSetup(exerciseController)
                .build();
    }

    @Before
    public void clearDb() {
        exerciseRepository.deleteAll();
    }

    @Test
    public void addNewExercise_Integration() throws Exception {
        ExerciseEntity exerciseEntity = new ExerciseEntity();
        exerciseEntity.setExerciseName("Shoulder Press");
        exerciseEntity.setMuscleGroup("Shoulder");

        ClassPathResource classPathResource = new ClassPathResource("/responses/addExercise.json");
        String expectedBody = new String(Files.readAllBytes(Paths.get(classPathResource.getURI())));

        mockMvc.perform(post("/exercises")
                .contentType(MediaType.APPLICATION_JSON)
                .content(expectedBody)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        //TODO: Find out where this exception is coming from
//                .andExpect(jsonPath("$.exerciseName", is("Shoulder Press")))
//                .andExpect(jsonPath("$.muscleGroup", is("Shoulder")));

        List<ExerciseEntity> exerciseEntityList = exerciseRepository.findAll();
        assertThat(exerciseEntityList, hasSize(1));
        ExerciseEntity actualExerciseEntity = exerciseEntityList.get(0);

        assertThat(actualExerciseEntity.getExerciseName(), is("Shoulder Press"));
        assertThat(actualExerciseEntity.getMuscleGroup(), is("Shoulder"));
    }
}