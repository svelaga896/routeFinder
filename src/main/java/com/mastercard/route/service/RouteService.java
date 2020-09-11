package com.mastercard.route.service;

import com.mastercard.route.repository.CityRouteLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RouteService {

    @Autowired
    CityRouteLoader cityRouteLoader;

    public boolean isRouteExists(String city1,String city2){
        return cityRouteLoader.getRouteMap().containsKey(city1)
                && cityRouteLoader.getRouteMap().get(city1).contains(city2);
    }

}
