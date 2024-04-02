package com.example.backend.service.impl.utils;

import com.example.backend.exception.BusinessException;
import com.example.backend.utils.CommonConstant;
import com.example.backend.utils.result.ResultData;
import com.example.backend.utils.result.ReturnCodes;
import lombok.extern.slf4j.Slf4j;
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
import java.util.Date;
import java.util.stream.Collectors;

import static com.example.backend.utils.CommonConstant.BACKUP_FOLDER;

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
@Slf4j
public class SqlScriptService {

//    @Autowired
//    private DataSource dataSource;

    @Value("${custom.mysql.username}")
    String databaseUser;
    @Value("${custom.mysql.password}")
    String databasePassword;
    @Value("${custom.mysql.host}")
    String databaseHost;
    @Value("${custom.mysql.port}")
    String databasePort;
    @Value("${custom.mysql.table}")
    String databaseTable;

    /**
     *  todo check一下这个注解真有效吗
     */
    @Transactional // 如果需要的话，添加事务注解
    public void runSqlScript(String path) {
//        String database = "club"; // 需要备份的数据库名
//        System.out.println("现在时间是" + new Date());
        Runtime runtime = Runtime.getRuntime();
        try {
            String stmt = "mysql -h "+databaseHost
                    +" -u"+databaseUser
                    +" -p"+databasePassword
                    +" "+databaseTable+"< " + path;
            log.info("还原数据库  " + stmt);
            String[] command = {"cmd", "/c", stmt};
            Process process = runtime.exec(command);
            //若有错误信息则输出
            InputStream errorStream = process.getErrorStream();
//            System.out.println(errorStream);
            //等待操作
            int processComplete = process.waitFor();
            if (processComplete == 0) {
                log.info("还原成功.");
            } else {
                String result = new BufferedReader(new InputStreamReader(errorStream, "GBK"))
                        .lines().collect(Collectors.joining(System.lineSeparator()));
                log.error(result);
                // 截取返回
                throw new BusinessException(ReturnCodes.SYSTEM_ERROR, result.substring(0, 50));
            }
        } catch (BusinessException e){
            throw e;
        } catch (Exception e) {
            throw new BusinessException(ReturnCodes.SYSTEM_ERROR,"还原数据库失败");
        }
    }

//    private void executeSql(String sql) {
//        try (Connection connection = dataSource.getConnection();
//             Statement statement = connection.createStatement()) {
//            statement.execute(sql);
//        } catch (SQLException e) {
//            throw new BusinessException(ReturnCodes.SYSTEM_ERROR,"执行SQL脚本错");
//        }
//    }

    public void backup() {

        Date date = new Date();

        // 定义CMD指令和参数作为数组元素
        String[] command = {
                "mysqldump",
                "-u" + databaseUser,
                "-p" + databasePassword,
                "--host=" + databaseHost,
                "--port=" + databasePort,
                "--databases",
                databaseTable,
                "-r", // 使用 -r 参数指定输出文件，而不是重定向
                BACKUP_FOLDER + "/" + date.toString().replace(" ","_").replace(":","_")+ ".sql"
        };

        // 使用String.join合并数组元素，元素之间用空格分隔
        String fullCommand = String.join(" ", command);
        System.out.println(fullCommand); // 打印完整的命令字符串

        // 创建ProcessBuilder对象
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        processBuilder.redirectErrorStream(true); // 合并标准输出和错误输出

        try {
            // 启动进程
            Process process = processBuilder.start();

            // 读取进程的输出
            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line); // 打印到控制台或根据需要处理
            }

            // 等待进程结束并获取退出值
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                System.err.println("mysqldump process exited with code: " + exitCode);
            } else {
                System.out.println("mysqldump process completed successfully.");
            }
        } catch (IOException | InterruptedException e) {
            throw new BusinessException(ReturnCodes.SYSTEM_ERROR, "数据库备份错误");
        }
    }
}
