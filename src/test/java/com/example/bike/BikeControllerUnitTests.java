package com.example.bike;


import com.example.bike.model.Bike;
import com.example.bike.repository.BikeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class BikeControllerUnitTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BikeRepository bikeRepository;

    //Mapper
    private ObjectMapper mapper = new ObjectMapper();




    //get all parts test
    @Test
    public void givenBikes_whenGetBikes_thenReturnJsonBikes() throws Exception
    {

        Bike bike1 = new Bike("kker", "Ka7la");
        Bike bike2 = new Bike("kker2", "Witte");

        List<Bike> bikes = new ArrayList<>();
        bikes.add(bike1);
        bikes.add(bike2);


        given(bikeRepository.findAll()).willReturn(bikes);

        mockMvc.perform(get("/bikes"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].model",is("kker")))
                .andExpect(jsonPath("$[0].color",is("Ka7la")))
                .andExpect(jsonPath("$[1].model",is("kker2")))
                .andExpect(jsonPath("$[1].color",is("Witte")));

    }

}
