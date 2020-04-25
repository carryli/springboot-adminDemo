package com.cloud.channel.backend.core.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud.channel.backend.core.ResponseResult;
import com.cloud.channel.backend.core.exception.BizException;
import com.cloud.channel.backend.core.exception.ResponseCodeEnum;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Bruce
 * @classname Saa
 * @description 全局异常处理器
 * @date 2020/4/24 0024 18:24
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 处理自定义的业务异常
     * 
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    public ResponseResult bizExceptionHandler(HttpServletRequest req, BizException e) {
        log.error("发生业务异常！原因是：{}", e.getMessage());
        return ResponseResult.error(e.getErrorCode(), e.getMessage());
    }

    /**
     * 处理其他异常
     * 
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseResult exceptionHandler(HttpServletRequest req, Exception e) {
        log.error("未知异常！原因是:", e);
        if (e instanceof MissingServletRequestParameterException || e instanceof MethodArgumentNotValidException) {
            return ResponseResult.error(ResponseCodeEnum.REQUEST_PARAMS_ERROR.getCode(), e.getMessage());
        }
        return ResponseResult.error(ResponseCodeEnum.INTERNAL_SERVER_ERROR.getCode(), e.getMessage());
    }
}
