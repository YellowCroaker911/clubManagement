package com.example.backend.exception;


import com.example.backend.utils.result.ResultData;
import com.example.backend.utils.result.ReturnCodes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(BusinessException.class)    // 捕获特定类型的异常
    public ResultData customExceptionHandler(BusinessException e){
        log.error("customException: " + e);
        if(e.getMessage() == null)return ResultData.fail(e.getCode(), null);
        else return ResultData.fail(e.getCode().getCode(), e.getMessage(), null);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResultData RuntimeExceptionHandler(RuntimeException e){
        log.error("runtimeException: " + e);
        return ResultData.fail(ReturnCodes.SYSTEM_ERROR, null);
    }

    @ExceptionHandler(Throwable.class)
    public ResultData RestExceptionHandler(Throwable e){
        log.error("other exception: ", e);
        return ResultData.fail(ReturnCodes.SYSTEM_ERROR, null);
    }
}
