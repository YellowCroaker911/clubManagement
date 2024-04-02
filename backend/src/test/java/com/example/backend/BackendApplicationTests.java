package com.example.backend;

import com.example.backend.service.AdminService;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.io.*;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Date;

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
    @Test
    void runSqlScriptTest() {
        String database = "club"; // 需要备份的数据库名
        System.out.println("现在时间是" + new Date());
        Runtime runtime = Runtime.getRuntime();
        try {
            String filePath =  "./backup/Mon_Apr_01_11_35_30_CST_2024.sql"; // sql文件路径
            String stmt = "mysql -h localhost -uroot -p123456 "+database+"< " + filePath;
            System.out.println(stmt);
            String[] command = {"cmd", "/c", stmt};
            Process process = runtime.exec(command);
            //若有错误信息则输出
            InputStream errorStream = process.getErrorStream();
            System.out.println(errorStream);
            //等待操作
            int processComplete = process.waitFor();
            if (processComplete == 0) {
                System.out.println("还原成功.");
            } else {
                throw new RuntimeException("还原数据库失败.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
