package cn.lichuachua.blog.blogserver.web.controller;

import cn.lichuachua.blog.blogserver.dto.TokenInfo;
import cn.lichuachua.blog.blogserver.dto.UserInfoDTO;
import cn.lichuachua.blog.blogserver.entity.User;
import cn.lichuachua.blog.blogserver.form.UserLoginForm;
import cn.lichuachua.blog.blogserver.form.UserRegisterForm;
import cn.lichuachua.blog.blogserver.service.IUserService;
import cn.lichuachua.blog.blogserver.wrapper.ResultWrapper;
import cn.lichuachua.blog.core.support.web.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author 李歘歘
 * 用户接口
 */
@Api(value = "UserController", tags = {"用户API"})
@RestController
@RequestMapping(value = "/user")
public class UserController extends BaseController<UserInfoDTO> {

    @Autowired
    private IUserService userService;

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public ResultWrapper register(
            @Valid UserRegisterForm userRegisterForm,
            BindingResult bindingResult)  {
        /**
         * 验证参数
         */
        validateParams(bindingResult);

        /**
         * 注册
         */
        userService.register(userRegisterForm);

        return ResultWrapper.success();
    }


    @ApiOperation("用户登录")
    @PostMapping("/login")
    public ResultWrapper login(
            @Valid @RequestBody UserLoginForm userLoginForm,
            BindingResult bindingResult) {
        /**
         * 验证登录
         */
        validateParams(bindingResult);

        /**
         * 登录
         */
        TokenInfo tokenInfo = userService.login(userLoginForm);
        /**
         * 返回token
         */
        return ResultWrapper.successWithData(tokenInfo);


    }


}
