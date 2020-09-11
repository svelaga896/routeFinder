package com.mastercard.route.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Repository
public class CityRouteLoader {

    @Value("classpath:cities.txt")
    Resource resource;

    private static Map<String, List<String>> routeMap = new HashMap<>();

    @PostConstruct
    void init() throws IOException {
        String delimiter =",";
        File file = resource.getFile();
        Stream<String> stream = Files.lines(file.toPath());
        stream.filter(line -> line.contains(delimiter)).forEach(
                line-> {
                    routeMap.computeIfAbsent(line.split(delimiter)[0].toLowerCase().replaceAll("\\s", ""), k -> new ArrayList<>()).add(line.split(delimiter)[1].toLowerCase().replaceAll("\\s", ""));
                    routeMap.computeIfAbsent(line.split(delimiter)[1].toLowerCase().replaceAll("\\s", ""), k -> new ArrayList<>()).add(line.split(delimiter)[0].toLowerCase().replaceAll("\\s", ""));
                }
        );
    }

    public Map<String, List<String>> getRouteMap(){
        return routeMap;
    }

}
