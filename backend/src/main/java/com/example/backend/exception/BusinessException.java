package com.example.backend.exception;

import com.example.backend.utils.result.ReturnCodes;
import lombok.Getter;


@Getter
public class BusinessException extends RuntimeException{
    private final ReturnCodes code;
    private String message = null;

    public BusinessException(ReturnCodes errorCode, String message){
        super(errorCode.getMessage());
        this.code = errorCode;
        this.message = message;
    }
    public BusinessException(ReturnCodes errorCode){
        super(errorCode.getMessage());
        this.code = errorCode;
    }
}
