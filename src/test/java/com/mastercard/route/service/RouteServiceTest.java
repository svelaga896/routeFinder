package com.mastercard.route.service;

import com.mastercard.route.repository.CityRouteLoader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
public class RouteServiceTest {

    @Mock
    private CityRouteLoader cityRouteLoader;

    @InjectMocks
    private RouteService routeService;

    @Mock
    private  HashMap<String, List<String>> routeMapTest;

    @Mock
    private  List<String> connectedRouteListTest;


    @Test
    public void isRouteDataExists() {
        //given, when , then - approach.

        Map<String, List<String>> routeMap = new HashMap<>();
        List<String> connectedRouteList = new ArrayList<>();
        connectedRouteList.add("atlanta");
        connectedRouteList.add("newyork");
        routeMap.put("boston",connectedRouteList);
        doReturn(routeMap).when(cityRouteLoader).getRouteMap();

        Map<String, List<String>> cityRoadMap = cityRouteLoader.getRouteMap();
        assertThat(routeMap).isEqualTo(cityRoadMap);

        assertTrue(routeService.isRouteExists("boston","newyork"));
        assertTrue(!routeService.isRouteExists("",""));
    }

    @Test
    public void isRouteMapExists() {
        doReturn(routeMapTest).when(cityRouteLoader).getRouteMap();
        doReturn(connectedRouteListTest).when(routeMapTest).get(Mockito.anyString());
        List<String> connectedRouteLst =  cityRouteLoader.getRouteMap().get("boston");
        assertThat(connectedRouteLst).isEqualTo(connectedRouteListTest);
    }

    @Test
    public void isTwoCitiesConnected() {
        doReturn(routeMapTest).when(cityRouteLoader).getRouteMap();
        doReturn(connectedRouteListTest).when(routeMapTest).get(Mockito.anyString());
        doReturn(true).when(connectedRouteListTest).contains(Mockito.anyString());
        boolean isConnected =  cityRouteLoader.getRouteMap().get("boston").contains("newyork");
        assertTrue(isConnected);
    }

}
