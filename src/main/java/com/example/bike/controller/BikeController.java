package com.example.bike.controller;


import com.example.bike.model.Bike;
import com.example.bike.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class BikeController {

    @Autowired
    private BikeRepository bikeRepository;

    @PostConstruct
    public  void fillDB()
    {
        if(bikeRepository.count() == 0)
        {
            bikeRepository.save(new Bike("kker", "Ka7la"));
            bikeRepository.save(new Bike("kker2", "Witte"));

        }

    }

    //get all bikes

    @GetMapping("/bikes")
    public List<Bike> getParts()
    {
        return  bikeRepository.findAll();
    }

}
