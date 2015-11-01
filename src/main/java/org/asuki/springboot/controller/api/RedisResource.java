package org.asuki.springboot.controller.api;

import com.google.common.hash.Hashing;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("redis")
public class RedisResource {

    private static final UrlValidator URL_VALIDATOR = new UrlValidator(new String[]{"http", "https"});

    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public void redirect(@PathVariable String id, HttpServletResponse resp) throws Exception {
        final String url = redisTemplate.opsForValue().get(id);
        if (url != null) {
            resp.sendRedirect(url);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    // RequestBody  https://www.google.com/search?q=learn+once
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> save(@RequestBody String url, HttpServletRequest req) {
        if (!URL_VALIDATOR.isValid(url)) {
            return new ResponseEntity<>(BAD_REQUEST);
        }

        final String id = Hashing.murmur3_32().hashString(url, UTF_8).toString();
        redisTemplate.opsForValue().set(id, url);

        return new ResponseEntity<>(req.getRequestURL() + "/" + id, OK);
    }
}
