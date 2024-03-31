package com.example.backend.service.impl;

import com.example.backend.exception.BusinessException;
import com.example.backend.model.vo.FileDetailsVO;
import com.example.backend.service.AdminService;
import com.example.backend.service.impl.utils.FileDetailsService;
import com.example.backend.utils.result.ResultData;
import com.example.backend.utils.result.ReturnCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.backend.utils.CommonConstant.BACKUP_FOLDER;

@Service
public class AdminServiceImpl implements AdminService {
    @Value("${custom.mysql.username}")
    String databaseUser;
    @Value("${custom.mysql.password}")
    String databasePassword;
    @Value("${custom.mysql.table}")
    String databaseTable;
    @Autowired
    FileDetailsService fileDetailsService;

    public ResultData<Object> backup() {

        Date date = new Date();

        // 定义CMD指令和参数作为数组元素
        String[] command = {
                "mysqldump",
                "-u" + databaseUser,
                "-p" + databasePassword,
                "--host=127.0.0.1",
                "--port=3306",
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
            e.printStackTrace();
        }

        return ResultData.success(null);
    }

    @Override
    public ResultData<List<FileDetailsVO>> getAllBackUpFiles() {
        List<FileDetailsVO> fileDetailsVOs =  fileDetailsService.getAllFiles(BACKUP_FOLDER);
        return ResultData.success(fileDetailsVOs);
    }


    @Override
    public ResultData<ResponseEntity<Resource>> download() {

        // 定义备份文件夹路径
        Path backupDirectory = Paths.get(BACKUP_FOLDER);

        Path filePath = null;

        try {
            // 获取文件夹下的所有文件
            List<Path> files = Files.list(backupDirectory)
                    .filter(Files::isRegularFile) // 过滤掉非文件项
                    .collect(Collectors.toList());

            // 如果文件夹为空，则直接返回
            if (files.isEmpty()) {
                System.out.println("No files found in the backup directory.");
                throw new BusinessException(ReturnCodes.SYSTEM_ERROR, "备份文件夹为空");
            }

            // 根据最后修改时间排序文件
            Collections.sort(files, Comparator.comparingLong(f -> {
                try {
                    return Files.getLastModifiedTime(f).toMillis();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }));

            // 获取最后修改时间最新的文件
            Path latestFile = files.get(files.size() - 1);
            System.out.println("The latest file is: " + latestFile);

            // 如果需要，你可以将此路径赋值给filePath变量
            filePath = latestFile;

        } catch (IOException e) {
            e.printStackTrace();
        }

        Resource resource = new FileSystemResource(filePath);

        // 如果文件不存在，则抛出异常
        if (!resource.exists() || !resource.isReadable()) {
            throw new BusinessException(ReturnCodes.SYSTEM_ERROR, "读文件错误");
        }

        // 设置文件名为要下载的文件名（不含路径）
        String fileName = filePath.getFileName().toString();

        // 创建HttpHeaders对象并设置Content-Disposition
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"");

        // 返回ResponseEntity对象，包含文件内容、状态码和HttpHeaders
        return ResultData.success(ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource));
    }
}