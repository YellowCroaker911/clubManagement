package com.example.backend.service;

import com.example.backend.model.vo.FileDetailsVO;
import com.example.backend.utils.result.ResultData;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface AdminService {
    ResultData<Object> backup();
    ResultData<List<FileDetailsVO>> getAllBackUpFiles();
    void download(HttpServletResponse response, String path) throws IOException;
    ResultData<Object> recovery(String path);
}
