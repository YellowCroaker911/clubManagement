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
        System.out.println(adminService.getAllBackUpFiles());
    }

}
