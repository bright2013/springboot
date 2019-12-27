/**
 * Copyright (C), 2019-2019, 软件卓越人才班
 * FileName: CustomerController
 * Author:   hzg
 * Date:     2019-12-23 14:37
 * Description: 客户Controller示例
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package org.example.controllers;

import org.example.models.Customer;
import org.example.repositories.CustomerRepository;
import org.example.utils.CustomerNotfoundException;
import org.example.utils.ResponseCode;
import org.example.utils.ResponseData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈客户Controller示例〉
 *
 * @author hbr
 * @create 2019-12-23
 * @since 1.0.0
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Resource
    CustomerRepository customerRepository;

    /*
     * 功能描述: <br>
     * 〈根据客户id查找客户〉
     *
     * @params: id
     * @return: customer
     * @since: 1.0.0
     * @Author: hbr
     * @Date: 2019-12-23 14:51
     */
    @GetMapping("/custid/{id}")
    public ResponseEntity<Object> findCustById(@PathVariable("id") long id){
        ResponseData responseData = new ResponseData();
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotfoundException());
        responseData.setResultCode(ResponseCode.SUCCESS.value());
        responseData.setResultData(customer);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Object> findAll(){
        ResponseData responseData = new ResponseData();
        List<Customer> customers = customerRepository.findAll();
        responseData.setResultCode(ResponseCode.SUCCESS.value());
        responseData.setResultData(customers);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    /*
     * 功能描述: <br>
     * 〈添加客户〉
     *
     * @params: Customer
     * @return: ResponseEntity
     * @since: 1.0.0
     * @Author: hzg
     * @Date: 2019-12-23 15:12
     */
    @PostMapping("/add")
    public ResponseEntity<Object> addCustomer(@RequestBody Customer customer){
        ResponseData responseData = new ResponseData();
        Customer savedCustomer = customerRepository.save(customer);
        responseData.setResultCode(ResponseCode.SUCCESS.value());
        responseData.setResultData(savedCustomer);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/one")
    public Customer getCustomer(){

        Customer customer = customerRepository.getOne(10001L);
       return customer;
    }



}
