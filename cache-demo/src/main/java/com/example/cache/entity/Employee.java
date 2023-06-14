package com.example.cache.entity;

import lombok.Data;

import java.io.Serializable;


/**
 * 员工类
 */
@Data
public class Employee implements Serializable {
	
	private Integer id;

	private String lastName;

	private String email;

	private Integer gender; //性别 1男  0女

	private Integer dId;

}
