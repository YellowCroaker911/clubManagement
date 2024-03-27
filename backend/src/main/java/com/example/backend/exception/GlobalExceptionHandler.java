package com.example.backend.exception;


import com.example.backend.utils.result.ResultData;
import com.example.backend.utils.result.ReturnCodes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.security.SignatureException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(BusinessException.class)    // 捕获特定类型的异常
    public ResultData customExceptionHandler(BusinessException e){
        log.info("customException: " + e);
        if(e.getMessage() == null)return ResultData.fail(e.getCode(), null);
        else return ResultData.fail(e.getCode().getCode(), e.getMessage(), null);
    }

    /**
     * 校验出错
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultData argumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        log.info("数据校验错误：" + e);
        return ResultData.fail(ReturnCodes.VALID_FAIL.getCode(), e.getBindingResult().getFieldError().getDefaultMessage(), null);
    }

    /**
     * 登录错误
     * @param e
     * @return
     */
    @ExceptionHandler(BadCredentialsException.class)
    public ResultData badCredentialsExceptionHandler(BadCredentialsException e) {
        log.info("数据校验错误：" + e);
        return ResultData.fail(ReturnCodes.VALID_FAIL.getCode(), e.getMessage(), null);
    }


    @ExceptionHandler(SignatureException.class)
    public ResultData jwtSignatureExceptionHandler(SignatureException e){
        log.info("错误的jwt" + e);
        return ResultData.fail(ReturnCodes.BAD_JWT, null);
    }


    @ExceptionHandler(AuthenticationException.class)
    public ResultData jwtSignatureExceptionHandler(AuthenticationException e){
        log.info("用户不存在 " + e);
        return ResultData.fail(ReturnCodes.USER_NOT_EXIST, null);
    }



    /******通用异常捕获*******/
    @ExceptionHandler(RuntimeException.class)
    public ResultData RuntimeExceptionHandler(RuntimeException e){
        log.error("runtimeException: " + e);
        log.error(e.getMessage());
        e.printStackTrace();
        return ResultData.fail(ReturnCodes.SYSTEM_ERROR, null);
    }

    @ExceptionHandler(Throwable.class)
    public ResultData RestExceptionHandler(Throwable e){
        log.error("other exception: ", e);
        log.error(e.getMessage());
        e.printStackTrace();
        return ResultData.fail(ReturnCodes.SYSTEM_ERROR, null);
    }
}
