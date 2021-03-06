package demo.integration;

import demo.Application;
import demo.data.ExerciseRepository;
import demo.exercise.ExerciseEntity;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class GetExercises {

    @Autowired
    ExerciseRepository exerciseRepository;

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
        exerciseRepository.deleteAll();
    }

    @Test
    public void returnAllAvailableExercises_Integration() throws Exception {
        ExerciseEntity exerciseEntity = new ExerciseEntity();
        exerciseEntity.setExerciseName("Chest Press");
        exerciseEntity.setMuscleGroup("Chest");

        ExerciseEntity exerciseEntity2 = new ExerciseEntity();
        exerciseEntity2.setExerciseName("Bicep Curl");
        exerciseEntity2.setMuscleGroup("Biceps");

        exerciseRepository.save(exerciseEntity);
        exerciseRepository.save(exerciseEntity2);

        ClassPathResource classPathResource = new ClassPathResource("/responses/getExercises.json");
        String expectedBody = new String(Files.readAllBytes(Paths.get(classPathResource.getURI())));

        mockMvc.perform(get("/exercises"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedBody));
    }
}
