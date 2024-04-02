package com.example.backend.service.impl;

import com.example.backend.exception.BusinessException;
import com.example.backend.model.vo.FileDetailsVO;
import com.example.backend.service.AdminService;
import com.example.backend.service.impl.utils.FileDetails;
import com.example.backend.service.impl.utils.FileDetailsService;
import com.example.backend.service.impl.utils.SqlScriptService;
import com.example.backend.utils.result.ResultData;
import com.example.backend.utils.result.ReturnCodes;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static com.example.backend.utils.CommonConstant.BACKUP_FOLDER;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    FileDetailsService fileDetailsService;
    @Autowired
    SqlScriptService sqlScriptService;

    public ResultData<Object> backup() {
        sqlScriptService.backup();
        return ResultData.success(null);
    }

    @Override
    public ResultData<List<FileDetailsVO>> getAllBackUpFiles() {
        List<FileDetails> fileDetailsList =  fileDetailsService.getAllFiles(BACKUP_FOLDER);
        List<FileDetailsVO> fileDetailsVOList = new ArrayList<>();
        for(FileDetails fileDetails: fileDetailsList ){
            FileDetailsVO fileDetailsVO = new FileDetailsVO();
            BeanUtils.copyProperties(fileDetails,fileDetailsVO);
            fileDetailsVOList.add(fileDetailsVO);
        }
        return ResultData.success(fileDetailsVOList);
    }


    @Override
    public ResultData<ResponseEntity<Resource>> download(String path) {

        Path filePath = Paths.get(path);

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

    @Override
    public ResultData<Object> recovery(String path) {
        sqlScriptService.runSqlScript(path);
        return ResultData.success(null);
    }
}