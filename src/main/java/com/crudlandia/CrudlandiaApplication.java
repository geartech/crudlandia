package com.crudlandia;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.geartech.app.mappers")
public class CrudlandiaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudlandiaApplication.class, args);
	}

}
