package com.cloud.channel.backend.core.handler;

import java.nio.file.AccessDeniedException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud.channel.backend.business.constant.ResponseCodeEnum;
import com.cloud.channel.backend.core.ResponseResult;
import com.cloud.channel.backend.core.exception.BizException;

import lombok.extern.slf4j.Slf4j;

/**
 * @author by Bruce
 * @description 响应异常处理器 构建返回结果集
 * @date
 **/

@Slf4j
@ControllerAdvice
public class ResponseHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseResult handleException(HttpServletRequest request, Exception ex) {

        int code = ResponseCodeEnum.SYSTEM_ERROR.getCode();
        if (ex instanceof HttpMessageNotReadableException) {
            code = ResponseCodeEnum.PARAM_ERROR.getCode();
        } else if (ex instanceof HttpRequestMethodNotSupportedException) {
            code = ResponseCodeEnum.HTTP_METHOD_NOT_SUPPORT.getCode();
        }
        String msg = "";

        if (ex instanceof BizException) {
            BizException bizException = (BizException)ex;
            msg = bizException.getMessage();
            code = bizException.getErrorCode().getCode();

        } else if (ex instanceof AccessDeniedException) {
            msg = ResponseCodeEnum.AUTH_FAILED.getDescription();
            code = ResponseCodeEnum.AUTH_FAILED.getCode();
        } else {
            msg = ResponseCodeEnum.SYSTEM_ERROR.getDescription();
        }
        log.error("code: " + code + "  msg: " + msg, ex);
        return ResponseResult.error(code, msg);
    }
}
