package com.example.bike;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.bike.model.Bike;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.example.bike.repository.BikeRepository;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BikeControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BikeRepository bikeRepository;

    private Bike bikeBike1 = new Bike("g001","Gazelle1");
    private Bike bikeBike2 = new Bike("g002","Gazelle2");
//    private Bike bicycleBicycle3 = new Bike("g003","Gazelle","Chamonix S8","Wit");
//    private Bike bicycleToBeDeleted = new Bike("g004","Gazelle","HMB Connect","Oranje");

    @BeforeEach
    public void beforeAllTests() {
        bikeRepository.deleteAll();
        bikeRepository.save(bikeBike1);
        bikeRepository.save(bikeBike2);
//        BikeRepository.save(bicycleBicycle3);
//        bicycleRepository.save(bicycleToBeDeleted);
    }

    @AfterEach
    public void afterAllTests() {
        bikeRepository.deleteAll();
    }

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void givenBicycle_whenGetAllBicycles_thenReturnJsonBicycles() throws Exception {

        List<Bike> bikeList = new ArrayList<>();
        bikeList.add(bikeBike1);
        bikeList.add(bikeBike2);
//        bicycleList.add(bicycleBicycle3);

        mockMvc.perform(get("/bikes"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
//                .andExpect(jsonPath("$[0].bicycleNumber", is("g001")))
//                .andExpect(jsonPath("$[0].brand", is("Gazelle")))
                .andExpect(jsonPath("$[0].model", is("g001")))
                .andExpect(jsonPath("$[0].color", is("Gazelle1")))
//                .andExpect(jsonPath("$[1].bicycleNumber", is("g002")))
//                .andExpect(jsonPath("$[1].brand", is("Gazelle")))
                .andExpect(jsonPath("$[1].model", is("g002")))
                .andExpect(jsonPath("$[1].color", is("Gazelle2")));
//                .andExpect(jsonPath("$[2].bicycleNumber", is("g003")))
//                .andExpect(jsonPath("$[2].brand", is("Gazelle")))
//                .andExpect(jsonPath("$[2].model", is("Chamonix S8")))
//                .andExpect(jsonPath("$[2].colour", is("Wit")));

    }
}
