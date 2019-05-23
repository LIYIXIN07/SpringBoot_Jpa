package cn.lichuachua.blog.blogserver.service;

import cn.lichuachua.blog.blogserver.dto.TokenInfo;
import cn.lichuachua.blog.blogserver.entity.User;
import cn.lichuachua.blog.blogserver.form.UserLoginForm;
import cn.lichuachua.blog.blogserver.form.UserRegisterForm;
import cn.lichuachua.blog.core.support.service.IBaseService;

import javax.validation.Valid;

/**
 * @author 李歘歘
 * 用户业务类接口
 */

public interface IUserService extends IBaseService<User, String> {
    /**
     * 用户注册
     * @param userRegisterForm
     */
    void register(@Valid UserRegisterForm userRegisterForm);

    /**
     * 登录
     * @param userLoginForm
     * @return
     */
    TokenInfo login(@Valid UserLoginForm userLoginForm);
}
