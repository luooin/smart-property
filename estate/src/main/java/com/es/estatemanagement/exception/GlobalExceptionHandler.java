package com.es.estatemanagement.exception;

import com.es.estatemanagement.common.MessageConstant;
import com.es.estatemanagement.common.Result;
import com.es.estatemanagement.common.StatusCode;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Auth: zhuan
 * @Desc: 全局异常处理器
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 功能描述: 捕获Exception类型的异常，并进行友好错误提示
     * @param exception
     * @return : com.es.estatemanagement.common.Result
     */
    @ExceptionHandler(value = Exception.class)
    public Result error(Exception exception){
        exception.printStackTrace();
        return new Result(false, StatusCode.ERROR, exception.getMessage());
    }


}
