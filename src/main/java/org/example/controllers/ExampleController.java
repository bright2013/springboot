/**
 * Copyright (C), 2019-2019, 软件卓越人才班
 * FileName: ExampleController
 * Author:   hzg
 * Date:     2019-12-27 08:40
 * Description: 示例跳转
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package org.example.controllers;

import org.example.models.Customer;
import org.example.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈示例跳转〉
 *
 * @author hzg
 * @create 2019-12-27
 * @since 1.0.0
 */

@Controller
@RequestMapping("/example")
public class ExampleController {


    @Resource
    CustomerRepository customerRepository;


    // /hello?name=kotlin
    @GetMapping("/hello")
    public String mainWithParam(
            @RequestParam(name = "name", required = false, defaultValue = "") String name, Model model) {

        model.addAttribute("message", name);

        return "welcome"; //view
    }

    @GetMapping("/element")
    public String mainWithParam( Model model) {

        return "elementui"; //view
    }

    @GetMapping("/customer")
    public String customerHome( Model model) {
        List<Customer> customers = customerRepository.findAll();
        model.addAttribute("customers", customers);
        return "customerview"; //view
    }

    @GetMapping("/custajax")
    public String customerAjaxHome( Model model) {
        return "customerajax"; //view
    }

    @GetMapping("/example")
    public String exampleHome( Model model) {
        return "blank"; //view
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
