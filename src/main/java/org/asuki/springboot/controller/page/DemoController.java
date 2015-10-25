package org.asuki.springboot.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.IntStream.rangeClosed;

@Controller
public class DemoController {

    @RequestMapping("/")
    public String index(Model model) {
        List<ViewData> list = new ArrayList<>();

        rangeClosed(1, 5).forEach(i -> list.add(new ViewData(i, "Message" + i)));

        model.addAttribute("msg", list);
        return "index";
    }
}

