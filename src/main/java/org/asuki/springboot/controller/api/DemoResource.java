package org.asuki.springboot.controller.api;

import org.asuki.springboot.controller.api.exception.CustomException;
import org.asuki.springboot.controller.api.model.Hoge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static java.lang.String.format;

@RestController
@RequestMapping("demo")
public class DemoResource {

    private static final Logger log = LoggerFactory.getLogger(DemoResource.class);

    @Value("${sample.value}")
    private String value;

    // localhost:8082/demo/123?name=Andy&location=Japan&location=UK
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Map<String, String> getMap(
            @PathVariable String id,
            @RequestParam(required = false) String name,
            @RequestParam Map<String, String> queryParameters,
            @RequestParam MultiValueMap<String, String> multiMap,
            @RequestHeader("Accept") String acceptHeader) {

        log.info(queryParameters.toString());
        log.info(multiMap.toString());

        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("name", name);
        map.put("location", queryParameters.get("location"));
        map.put("accept", acceptHeader);
        map.put("value", value);

        return map;
    }

    // {"id": 123, "name": "Andy"}
    @RequestMapping(method = RequestMethod.POST)
    public Hoge post(@RequestBody Hoge hoge) {

        return hoge;
    }

    @RequestMapping(value = "error", method = RequestMethod.GET)
    public void occurCustomException() {
        throw new CustomException("test exception");
    }

    @RequestMapping(value = "null", method = RequestMethod.GET)
    public void occurOtherException() {
        throw new NullPointerException("test exception");
    }

    @ExceptionHandler(NullPointerException.class)
    public String handleException(NullPointerException e) {
        return format("{\"error\":\"%s\"}", e.getMessage());
    }
}
