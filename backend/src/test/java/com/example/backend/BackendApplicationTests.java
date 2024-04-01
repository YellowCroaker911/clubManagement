package com.example.backend;

import com.example.backend.service.AdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BackendApplicationTests {

    @Autowired
    AdminService adminService;
    @Test
    void contextLoads() {
//        adminService.backup();
        adminService.recovery("F:\\Files\\大三下\\数据库\\ClubManagement\\backend\\backup\\Sun_Mar_31_23_53_09_CST_2024.sql");
//        System.out.println(adminService.recovery("./backup/Sun_Mar_31_23_53_09_CST_2024.sql"));
    }

}
