package com.es.estatemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import tk.mybatis.spring.annotation.MapperScan;

//@EnableTransactionManagement(proxyTargetClass=true)
@SpringBootApplication
//注意：@MapperScan注解要导入TK包下的
@MapperScan(basePackages = "com.es.estatemanagement.mapper")
public class EstateManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(EstateManagementApplication.class, args);
    }
}
