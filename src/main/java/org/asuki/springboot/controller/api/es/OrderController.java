package org.asuki.springboot.controller.api.es;

import org.asuki.springboot.model.es.Order;
import org.asuki.springboot.repository.es.OrderRepository;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.joda.time.DateTime;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogram;
import org.elasticsearch.search.aggregations.metrics.sum.Sum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

// localhost:8082/order localhost:9200/order
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private Client client;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Order addOrder(@RequestBody Order order) {
        return orderRepository.save(order);
    }

    @RequestMapping(value = "/summary", method = RequestMethod.GET)
    @ResponseBody
    public Map<DateTime, Double> orderSummary() throws Exception {

        SearchResponse searchResponse = client.prepareSearch("order")
                .addAggregation(
                        AggregationBuilders
                                .dateHistogram("monthly")
                                .field("createdAt")
                                .interval(DateHistogram.Interval.MONTH)
                                .subAggregation(
                                        AggregationBuilders
                                                .sum("total")
                                                .field("amount")))
                .execute().get();

        DateHistogram dateHistogram = searchResponse.getAggregations().get("monthly");

        Map<DateTime, Double> result = new HashMap<>();
        for (DateHistogram.Bucket entry : dateHistogram.getBuckets()) {
            DateTime keyAsDate = entry.getKeyAsDate();
            Sum sum = entry.getAggregations().get("total");
            result.put(keyAsDate, sum.getValue());
        }
        return result;
    }
}
