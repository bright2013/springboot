/**
 * Copyright (C), 2019-2019, 软件卓越人才班
 * FileName: StudentController
 * Author:   hzg
 * Date:     2019-12-27 11:19
 * Description: 学生页面的处理
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package org.example.controllers;

import com.sun.security.auth.UserPrincipal;
import org.example.models.Customer;
import org.example.models.Report;
import org.example.models.User;
import org.example.repositories.ReportRepository;
import org.example.repositories.UserRepository;
import org.example.utils.ResponseCode;
import org.example.utils.ResponseData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.Date;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈学生页面的处理〉
 *
 * @author hzg
 * @create 2019-12-27
 * @since 1.0.0
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Resource
    ReportRepository reportRepository;

    @Resource
    UserRepository userRepository;

    @RequestMapping("/addreport")
    public ResponseEntity<Object> addReport(@RequestBody Report report){

        // 获取当前登陆用户信息
        String username = "";
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        //根据用户名查找用户信息并保存到数据库
        User user = userRepository.findByUsernameEquals(username);
        report.setCreatedGroup(user.getGroupname());
        report.setCreatedPerson(user.getUsername());
        report.setCreatedId(user.getUserId());
        report.setCreatedAt(new Date());

        ResponseData responseData = new ResponseData();
        Report savedReport = reportRepository.save(report);
        responseData.setResultCode(ResponseCode.SUCCESS.value());
        responseData.setResultData(savedReport);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }


    @RequestMapping("/allmyreports")
    public ResponseEntity<Object> allMyReport(){

        // 获取当前登陆用户信息
        String username = "";
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
             username = ((UserDetails)principal).getUsername();
        } else {
             username = principal.toString();
        }

        ResponseData responseData = new ResponseData();
        List<Report> reports = reportRepository.findAllByCreatedPersonEquals(username);
        responseData.setResultCode(ResponseCode.SUCCESS.value());
        responseData.setResultData(reports);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }




}
