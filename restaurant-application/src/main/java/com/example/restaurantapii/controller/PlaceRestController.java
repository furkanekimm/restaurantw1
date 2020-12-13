package com.example.restaurantapii.controller;


import com.example.restaurantapii.dto.PlaceRestDTO;
import com.example.restaurantapii.services.PlaceRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/place")
public class PlaceRestController {
    @Autowired
    private PlaceRestService placeRestService;

    @PostMapping("/add")
    public PlaceRestDTO addPlaceRest(@RequestBody PlaceRestDTO placeRestDTO){
        return placeRestService.addPlaceRest(placeRestDTO);
    }

    @GetMapping("/list")
    public List<PlaceRestDTO> placeRestList(){
        return placeRestService.listPlaceRests();
    }

    @DeleteMapping("/delete/{id}")
    public Boolean deletePlaceRest(@PathVariable Long id){
        return placeRestService.deletePlaceRest(id);
    }

    @PutMapping("/update")
    public  PlaceRestDTO updatePlace(@RequestBody PlaceRestDTO placeRestDTO){
        return placeRestService.updatePlaceRest(placeRestDTO);
    }

}
