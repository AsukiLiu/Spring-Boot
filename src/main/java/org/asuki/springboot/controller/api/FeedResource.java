package org.asuki.springboot.controller.api;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

@RestController
@RequestMapping("api/feed")
public class FeedResource {

    private static final Logger log = LoggerFactory.getLogger(FeedResource.class);

    private List<Feed> feeds;

    {
        Feed feed1 = new Feed();
        feed1.setId(1);
        feed1.setName("Blog");
        feed1.setUrl("https://sites.google.com/site/webdevelopart/");

        Feed feed2 = new Feed();
        feed2.setId(2);
        feed2.setName("GitHub");
        feed2.setUrl("https://github.com/AsukiLiu");

        feeds = new ArrayList<>(asList(feed1, feed2));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Feed>> getFeeds(@RequestParam Map<String, String> queryParameters) {
        log.info(queryParameters.toString());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Paging-Info", "3");

        return new ResponseEntity<>(feeds, headers, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addFeed(@RequestBody Feed feed) {
        feed.setId(feeds.size() + 1);

        log.info(feed.toString());

        feeds.add(feed);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public void editFeed(@PathVariable int id, @RequestBody Feed feed) {
        Feed original = findById(id);
        original.setName(feed.getName());
        original.setUrl(feed.getUrl());
    }


    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deleteFeed(@PathVariable int id) {
        feeds.remove(findById(id));
    }

    private Feed findById(int id) {
        return feeds.stream().filter(f -> f.getId() == id).findAny().get();
    }

    @Getter
    @Setter
    @ToString
    public static class Feed {
        private int id;
        private String name;
        private String url;
    }
}
