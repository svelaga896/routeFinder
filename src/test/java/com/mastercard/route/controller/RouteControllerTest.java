package com.mastercard.route.controller;


import com.mastercard.route.service.RouteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.when;

@WebMvcTest(controllers = RouteController.class)
@ActiveProfiles("test")
public class RouteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RouteService routeService;

    @Test
    public void testRouteExists() throws Exception{
        when(routeService.isRouteExists("boston","newyork")).thenReturn(true);

        String result = mockMvc.perform(MockMvcRequestBuilders
                .get("/connected").param("origin", "boston")
                .param("destination", "newyork"))
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertEquals("yes", result);

    }

    @Test
    public void testRouteNotExists() throws Exception{
        when(routeService.isRouteExists("","")).thenReturn(false);

        String result = mockMvc.perform(MockMvcRequestBuilders
                .get("/connected").param("origin", "boston")
                .param("destination", "newyork"))
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertEquals("no", result);

    }


}
