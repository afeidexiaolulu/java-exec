package com.example.cache.service;

import com.example.cache.entity.Employee;

/**
 * @author lwj
 * @version 1.00
 * @time 2021/7/2 0002  下午 3:41
 */
public interface CacheService {


    //通过id查询
    Employee getEmpById(String id);

    //没有参数
    Employee getEmp();


    Employee saveEmp(Employee employee);
}
