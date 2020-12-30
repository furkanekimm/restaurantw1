package com.example.restaurantapii.controller;

import com.example.restaurantapii.dto.WaiterDTO;
import com.example.restaurantapii.services.WaiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/waiter")
@Validated
public class WaiterController {

    @Autowired
    private WaiterService waiterService;

    @PostMapping("/add")
    public Boolean addWaiter(@Valid @RequestBody WaiterDTO waiterDTO){
        return waiterService.addWaiter(waiterDTO);
    }

    @PutMapping("/")
    public WaiterDTO updateWaiter(@RequestBody WaiterDTO waiterDTO){
        return waiterService.updateWaiter(waiterDTO);
    }

    @GetMapping("/{id}")
    public WaiterDTO getWaiterByID(@NotNull(message = "{ID_NULL}") @Min(value = 0,message = "{ID_NOT_BE_LITTLE_ZERO}") @PathVariable Long id){
        return waiterService.getWaiterByID(id);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteWaiter(@NotNull(message = "{ID_NULL}") @Min(value = 0,message = "{ID_NOT_BE_LITTLE_ZERO}") @PathVariable Long id){
        return waiterService.deleteWaiter(id);
    }

    @GetMapping("/list")
    public List<WaiterDTO> getAllWaiter(){
        return waiterService.listAllWaiters();
    }

}
