package cn.lichuachua.blog.blogserver.enums;

import lombok.Getter;

/**
 * @author 李歘歘
 */
@Getter
public enum  ErrorCodeEnum {

    /**
     * 手机号已经被注册
     */
    MOBILE_REGISTERED(1111, "该手机号已被注册"),

    /**
     * 手机号还没注册成为系统的账号
     */
    MOBILE_NOT_REGISTERED(1111, "该手机号还未注册"),
    TWO_PASSWORD_NO_EQUALS(1111, "两次密码不一样"),
    /**
     * 用户密码错误
     */
    PASSWORD_ERROR(1111, "密码错误"),

    UNAUTHORIZED(1201, "未认证"),

    BAD_ACCESS_TOKEN(1201, "错误的token"),
    ERROR_USER_ID(1111, "错误的USER_ID"),
    NONE_FRIEND_APPLY(1111, "不存在的好友申请"),
    INVALID_APPLY_STATUS(1111, "申请状态错误"),
    ERROR_USER(1222,"该用户不存在"),

    /**
     * 云友圈权限存在，不必再次加入
     */
    JURISDICTION(1222, "该权限存在，不必再次加入"),
    /**
     * 文章不存在或者非此用户所发布  不能操作
     * @param code
     * @param message
     */
    CIRCLE_NO_EXIT(12224,"操作错误"),
    /**
     * 评论不存在或非用户所发，无权限删除
     */
    COMMENT_NO_EXIT(12223, "操作不正确");



    ErrorCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误信息
     */
    private String message;
}
