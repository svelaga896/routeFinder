package com.mastercard.route.controller;

import com.mastercard.route.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@RestController
public class RouteController {

    @Autowired
    RouteService service;

    @GetMapping("/connected")
    public String isConnected(@RequestParam Optional<String> origin, @RequestParam Optional<String> destination) throws IOException {
        String isConnected ="no";
        if(origin.isPresent() && destination.isPresent()){
            if(service.isRouteExists(origin.get().toLowerCase().replaceAll("\\s", ""),destination.get().toLowerCase().replaceAll("\\s", "")))
                isConnected = "yes";
        }
        return isConnected;
    }
}
