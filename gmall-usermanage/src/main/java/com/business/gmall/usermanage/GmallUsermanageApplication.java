package com.business.gmall.usermanage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.business.gmall.usermanage.mapper")
public class GmallUsermanageApplication {

	public static void main(String[] args) {
		SpringApplication.run(GmallUsermanageApplication.class, args);
	}

}
