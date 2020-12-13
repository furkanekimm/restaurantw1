package com.example.restaurantapii.controller;

import com.example.restaurantapii.dto.WaiterDTO;
import com.example.restaurantapii.services.WaiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/waiter")
public class WaiterController {

    @Autowired
    private WaiterService waiterService;

    @PostMapping("/add")
    public Boolean addWaiter(@RequestBody WaiterDTO waiterDTO){
        return waiterService.addWaiter(waiterDTO);
    }

    @PutMapping("/")
    public WaiterDTO updateWaiter(@RequestBody WaiterDTO waiterDTO){
        return waiterService.updateWaiter(waiterDTO);
    }

    @GetMapping("/{id}")
    public WaiterDTO getWaiterByID(@PathVariable Long id){
        return waiterService.getWaiterByID(id);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteWaiter(@PathVariable Long id){
        return waiterService.deleteWaiter(id);
    }

    @GetMapping("/list")
    public List<WaiterDTO> getAllWaiter(){
        return waiterService.listAllWaiters();
    }

}
