package com.example.restaurantapii.controller;

import com.example.restaurantapii.dto.InfoProfileDTO;
import com.example.restaurantapii.services.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/info/")
@CrossOrigin(origins = "*")
public class InfoController {

    @Autowired
    private InfoService infoService;


    @GetMapping("info-server")
    public List<InfoProfileDTO> getServerPort(){
       return infoService.getServerPort();
    }

}
