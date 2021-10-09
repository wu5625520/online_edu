package com.wxy.servicebase.handler;

import com.sun.xml.internal.ws.spi.db.DatabindingException;
import com.wxy.utils.StandardResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @author wxy
 * @title: GlobalException
 * @description: TODO
 * @date 2021/9/2616:59
 */

// 这个异常处理，是不是只有程序中的异常没有被处理才会触发?
@ControllerAdvice
@Slf4j
public class GlobalException {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public StandardResult globalExceptionHandler(Exception e) {
        e.printStackTrace();
//        System.out.println("全局异常执行了!");
        log.error("全局异常执行了2" + new Date().toString());
        return StandardResult.error().message("全局异常执行了!");
    }
}
