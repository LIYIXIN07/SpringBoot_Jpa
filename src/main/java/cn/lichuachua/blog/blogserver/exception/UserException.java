package cn.lichuachua.blog.blogserver.exception;

import cn.lichuachua.blog.blogserver.enums.ErrorCodeEnum;

/**
 * @author 李歘歘
 */
public class UserException extends BaseException {
    public UserException(int code, String message) {
        super(code, message);
    }
    public UserException(ErrorCodeEnum codeEnum, Object... args) {
        super(codeEnum, args);
    }
}
