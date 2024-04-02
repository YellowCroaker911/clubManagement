package com.example.backend.service.impl;

import com.example.backend.exception.BusinessException;
import com.example.backend.model.vo.FileDetailsVO;
import com.example.backend.service.AdminService;
import com.example.backend.service.impl.utils.FileDetails;
import com.example.backend.service.impl.utils.FileDetailsService;
import com.example.backend.service.impl.utils.SqlScriptService;
import com.example.backend.utils.CommonConstant;
import com.example.backend.utils.result.ResultData;
import com.example.backend.utils.result.ReturnCodes;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
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
    public void download(HttpServletResponse response, String path) throws IOException {

        File file = new File(BACKUP_FOLDER + "/" + path);

        response.reset();
        response.setContentType("application/x-download;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename="+ new String(file.getName().getBytes("utf-8"), "utf-8"));

        //创建输入流
        InputStream is = Files.newInputStream(file.toPath());
        OutputStream os = response.getOutputStream();

        //利用IOUtils将输入流的内容 复制到输出流
        //org.apache.tomcat.util.http.fileupload.IOUtils
        //项目搭建是自动集成了这个类 直接使用即可
        IOUtils.copy(is, os);
        os.flush();
        is.close();
        os.close();
    }

    @Override
    public ResultData<Object> recovery(String path) {
        sqlScriptService.runSqlScript(path);
        return ResultData.success(null);
    }
}