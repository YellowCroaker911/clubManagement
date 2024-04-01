package com.example.backend.service.impl.utils;

import com.example.backend.exception.BusinessException;
import com.example.backend.utils.result.ReturnCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

//@Service
//public class SqlScriptService {
//
//    @Value("${custom.mysql.table}")
//    String databaseTable;
//
//    @Autowired
//    private DataSource dataSource;
//
//    @Transactional // 如果需要的话，添加事务注解
//    public void runSqlScript(String path) {
////        executeSql("select * from user;");
//        executeSql("source " + path);
//    }
//
//    private void executeSql(String sql) {
//        try (Connection connection = dataSource.getConnection();
//             Statement statement = connection.createStatement()) {
//            statement.execute(sql);
//        } catch (SQLException e) {
//            throw new BusinessException(ReturnCodes.SYSTEM_ERROR,"执行SQL脚本错");
//        }
//    }
//}

//@Service
//public class SqlScriptService {
//
//    @Value("${custom.mysql.username}")
//    String databaseUser;
//    @Value("${custom.mysql.password}")
//    String databasePassword;
//    @Value("${custom.mysql.host}")
//    String databaseHost;
//    @Value("${custom.mysql.port}")
//    String databasePort;
//    @Value("${custom.mysql.table}")
//    String databaseTable;
//
//    public void runSqlScript(String path) {
//        String[] command = {
//                "mysql",
//                "-h", databaseHost,
//                "-P", databasePort,
//                "-u", databaseUser,
//                "-p" + databasePassword // 注意：这样做存在安全隐患，应使用更安全的方式
//        };
//
//        // 使用String.join合并数组元素，元素之间用空格分隔
//        String fullCommand = String.join(" ", command);
//        System.out.println(fullCommand); // 打印完整的命令字符串
//
//        ProcessBuilder processBuilder = new ProcessBuilder(command);
//        processBuilder.redirectErrorStream(true);
//
//        try {
//            Process process = processBuilder.start();
//
//            try (
//                    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
//            ) {
//                // 发送USE命令到MySQL客户端
//                System.out.println("USE " + databaseTable + "\n");
//                writer.write("USE " + databaseTable + "\n");
//                writer.flush();
//
//                // 发送source命令到MySQL客户端
//                // 注意：source命令需要通过MySQL客户端的交互式接口发送，而不是作为命令行参数
//                System.out.println("source " + path + "\n");
//                writer.write("source " + path + "\n");
//                writer.flush();
//
//                // 读取并输出SQL脚本执行过程中的任何输出
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    System.out.println(line);
//                }
//            }
//            int exitCode = process.waitFor();
//            if (exitCode != 0) {
//                throw new RuntimeException("执行SQL脚本出错，退出码：" + exitCode);
//            }
//        } catch (IOException | InterruptedException e) {
//            throw new RuntimeException("执行SQL脚本时发生错误：" + e.getMessage(), e);
//        }
//    }
//}


@Service
public class SqlScriptService {

    @Autowired
    private DataSource dataSource;

    @Transactional // 如果需要的话，添加事务注解
    public void runSqlScript(String path) {
        Resource scriptResource = new FileSystemResource(path);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(scriptResource.getInputStream()))) {
            String line;
            StringBuilder sql = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                // 跳过注释和空行
                if (!line.trim().startsWith("--") && !line.trim().isEmpty()) {
                    sql.append(line).append("\n");

                    // 如果行以分号结束，则执行SQL
                    if (line.endsWith(";")) {
                        executeSql(sql.toString());
                        sql.setLength(0); // 重置StringBuilder
                    }
                }
            }
            // 处理SQL脚本的最后一部分（如果没有以分号结束）
            if (sql.length() > 0) {
                executeSql(sql.toString());
            }
        } catch (IOException e) {
            throw new BusinessException(ReturnCodes.SYSTEM_ERROR,"读SQL脚本错");
        }
    }

    private void executeSql(String sql) {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            throw new BusinessException(ReturnCodes.SYSTEM_ERROR,"执行SQL脚本错");
        }
    }
}
