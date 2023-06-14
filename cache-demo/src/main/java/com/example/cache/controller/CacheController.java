package com.example.cache.controller;

import com.example.cache.entity.Employee;
import com.example.cache.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 缓存测试的
 *
 * @author lwj
 * @version 1.00
 * @time 2021/7/2 0002  下午 2:57
 */
@RestController
@RequestMapping("/cache")
public class CacheController {

    @Autowired
    private CacheService cacheService;

    @GetMapping("/emp")
    public Employee getEmpById(@RequestParam("id") String id) {
        Employee e = cacheService.getEmpById(id);
        return e;
    }


    @GetMapping("/emp2")
    public Employee getEmp() {
        Employee e = cacheService.getEmp();
        return e;
    }



    @PostMapping("/saveEmp")
    public void saveEmp(Employee employee){
        cacheService.saveEmp(employee);
    }
}
