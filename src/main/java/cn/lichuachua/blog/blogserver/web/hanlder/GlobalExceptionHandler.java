package cn.lichuachua.blog.blogserver.web.hanlder;

import cn.lichuachua.blog.blogserver.wrapper.ResultWrapper;
import cn.lichuachua.blog.commom.enums.BaseErrorCodeEnum;
import cn.lichuachua.blog.core.exception.SysException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 李歘歘
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultWrapper<?> handle(Exception e){
        e.printStackTrace();
        if (e instanceof SysException){
            return ResultWrapper.failure(((SysException) e).getCode(),e.getMessage());
        }
        return ResultWrapper.failure(BaseErrorCodeEnum.UN_KNOW_EXCEPTION.getCode(), BaseErrorCodeEnum.UN_KNOW_EXCEPTION.getMessage());
    }


}
