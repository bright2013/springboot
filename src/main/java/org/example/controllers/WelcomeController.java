package org.example.controllers;

import org.example.models.Customer;
import org.example.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Controller
public class WelcomeController {


    // inject via application.properties
    @Value("${welcome.message}")
    private String message;

    private List<String> tasks = Arrays.asList("张三", "李四", "王五");


    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("message", message);
        model.addAttribute("tasks", tasks);

        return "welcome"; //view
    }

    @GetMapping("/login")
    public String login(Model model) {

        return "loginpage"; //view
    }

    @GetMapping("/write")
    public String writePaper( Model model) {
        return "writepaper"; //view
    }

    @GetMapping("/myreport")
    public String myreport( Model model) {
        return "reportlist"; //view
    }

}