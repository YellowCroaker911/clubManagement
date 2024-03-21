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
        resultData.setStatus(100);
        resultData.setMessage(ReturnCodes.returnCodes.get(100));
        resultData.setData(data);
        return resultData;
    }

    public static <T> ResultData<T> fail(int code, T data) {
        ResultData<T> resultData = new ResultData<>();
        resultData.setStatus(code);
        resultData.setMessage(ReturnCodes.returnCodes.get(code));
        return resultData;
    }

    public T getData() {
        return data;
    }
}
