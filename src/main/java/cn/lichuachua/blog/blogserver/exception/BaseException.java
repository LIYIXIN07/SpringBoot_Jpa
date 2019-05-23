package cn.lichuachua.blog.blogserver.exception;

import cn.lichuachua.blog.blogserver.enums.ErrorCodeEnum;
import cn.lichuachua.blog.commom.enums.BaseErrorCodeEnum;
import cn.lichuachua.blog.core.exception.SysException;
import org.omg.PortableInterceptor.INACTIVE;

/**
 * @author  李歘歘
 */

public class BaseException extends SysException{

    public BaseException(int code, String message){
        super(message);
        this.code = code;
    }
    public BaseException(BaseErrorCodeEnum codeEnum, Object... args){super(codeEnum, args);}

    public BaseException(ErrorCodeEnum codeEnum, Object... args) {
        super(String.format(codeEnum.getMessage(), args));
        this.code = codeEnum.getCode();
    }
}
