package com.example.restaurantapii.controller;


import com.example.restaurantapii.dto.PlaceRestDTO;
import com.example.restaurantapii.services.PlaceRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/place")
@Validated
public class PlaceRestController {
    @Autowired
    private PlaceRestService placeRestService;

    @PostMapping("/add")
    public PlaceRestDTO addPlaceRest(@Valid @RequestBody PlaceRestDTO placeRestDTO){
        return placeRestService.addPlaceRest(placeRestDTO);
    }

    @GetMapping("/list")
    public List<PlaceRestDTO> placeRestList(){
        return placeRestService.listPlaceRests();
    }

    @DeleteMapping("/delete/{id}")
    public Boolean deletePlaceRest(@NotNull(message = "{ID_NULL}") @Min(value = 0,message = "{ID_NOT_BE_LITTLE_ZERO}") @PathVariable Long id){
        return placeRestService.deletePlaceRest(id);
    }

    @PutMapping("/update")
    public  PlaceRestDTO updatePlace(@RequestBody PlaceRestDTO placeRestDTO){
        return placeRestService.updatePlaceRest(placeRestDTO);
    }

    @GetMapping("/{id}")
    public PlaceRestDTO getTableById(@NotNull(message = "{ID_NULL}") @Min(value = 0,message = "{ID_NOT_BE_LITTLE_ZERO}") @PathVariable Long id){
        return placeRestService.getTableById(id);
    }

}
