package org.asuki.springboot.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    // localhost:8082/demo/123?arg2=456
    @RequestMapping("demo/{arg1}")
    public Map<String, String> getMap(
            @PathVariable String arg1,
            @RequestParam(required = false) String arg2) {

        Map<String, String> map = new HashMap<>();
        map.put("arg1", arg1);
        map.put("arg2", arg2);

        return map;
    }
}
