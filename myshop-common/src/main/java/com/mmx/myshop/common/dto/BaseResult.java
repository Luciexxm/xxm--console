package com.mmx.myshop.common.dto;

import com.mmx.myshop.common.constants.HttpStatusConstant;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class BaseResult implements Serializable {
    // HTTP 状态码
    private int status;
    private String message;
    private Error error;
    private Object data;

    // 数据笔数
    private int count;

    /**
     * 请求成功
     *
     * @param data
     * @return
     */
    public static BaseResult success(Object data) {
        int count = 0;

        if (data != null) {
            if (data instanceof List) {
                count = ((List) data).size();
            }

            else if (data instanceof Map) {
                count = ((Map) data).size();
            }

            else {
                count = 1;
            }
        }

        return createResult(200, data, null, count);
    }

    /**
     * 请求成功
     *
     * @param message
     * @return
     */
    public static BaseResult success(String message) {
        return createResult(200, null, message, 0);
    }

    /**
     * 请求失败
     * @return
     */
    public static BaseResult fail() {
        BaseResult baseResult = createResult(HttpStatusConstant.HTTP_500, null, HttpStatusConstant.HTTP_500_MESSAGE, 0);
        Error error = createError(HttpStatusConstant.HTTP_500_CODE, HttpStatusConstant.HTTP_500_CAUSE);
        baseResult.setError(error);
        return baseResult;
    }

    /**
     * 请求失败
     * @param status 状态码
     * @param message 错误消息
     * @param code 错误代码
     * @param cause 错误原因
     * @return
     */
    public static BaseResult fail(int status, String message, String code, String cause) {
        BaseResult baseResult = createResult(status, null, message, 0);
        Error error = createError(code, cause);
        baseResult.setError(error);
        return baseResult;
    }

    /**
     * 创建结构体
     *
     * @param status
     * @param data
     * @param message
     * @return
     */
    private static BaseResult createResult(int status, Object data, String message, int count) {
        BaseResult baseResult = new BaseResult();
        baseResult.setStatus(status);
        baseResult.setMessage(message);
        baseResult.setData(data);
        baseResult.setCount(count);
        return baseResult;
    }

    private static Error createError(String code, String cause) {
        Error error = new Error();
        error.setCode(code);
        error.setCause(cause);
        error.setServerTime(new Date());
        return error;
    }

    @Data
    public static class Error {
        // 错误码
        private String code;
        // 错误原因
        private String cause;
        private Date serverTime;
    }
}
