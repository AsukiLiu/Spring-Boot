package org.asuki.springboot.controller.page;

import org.asuki.springboot.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DemoController {

    @Autowired
    private DemoService service;

    @RequestMapping("/thymeleaf")
    public String thymeleafPage(Model model) {
        model.addAttribute("msg", service.findData());
        return "thymeleaf";
    }

    @RequestMapping("/angular")
    public String angularPage() {
        return "angular";
    }
}

