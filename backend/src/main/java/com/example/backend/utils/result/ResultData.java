package com.example.backend.utils.result;

import lombok.Data;

@Data
public class ResultData<T> {
    private int status;
    private String message;
    private T data;
    private long timestamp ;

    public ResultData (){
        this.timestamp = System.currentTimeMillis();
    }

    public static <T> ResultData<T> success(T data) {
        ResultData<T> resultData = new ResultData<>();
        resultData.setStatus(ReturnCodes.SUCCESS.getCode());
        resultData.setMessage(ReturnCodes.SUCCESS.getMessage());
        resultData.setData(data);
        return resultData;
    }

    public static <T> ResultData<T> fail(ReturnCodes code, T data) {
        ResultData<T> resultData = new ResultData<>();
        resultData.setStatus(code.getCode());
        resultData.setMessage(code.getMessage());
        return resultData;
    }

    public static <T> ResultData<T> fail(int code, String message, T data) {
        ResultData<T> resultData = new ResultData<>();
        resultData.setStatus(code);
        resultData.setMessage(message);
        return resultData;
    }
}
