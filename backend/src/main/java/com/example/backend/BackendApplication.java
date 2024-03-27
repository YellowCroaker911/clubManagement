package com.example.backend;

import com.example.backend.utils.CommonConstant;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
@MapperScan(basePackages = "com.example.backend.mapper")
public class BackendApplication {

    public static void main(String[] args) {
        configDir();
        SpringApplication.run(BackendApplication.class, args);
    }

    private static void configDir() {
        File file = new File(CommonConstant.IMG_FOLDER);
        if(!file.exists()) {
            file.mkdir();
        }
    }

}
