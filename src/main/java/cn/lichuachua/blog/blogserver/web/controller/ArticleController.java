package cn.lichuachua.blog.blogserver.web.controller;

import cn.lichuachua.blog.blogserver.dto.UserInfoDTO;
import cn.lichuachua.blog.blogserver.form.ArticlePublishForm;
import cn.lichuachua.blog.blogserver.service.IArticleService;
import cn.lichuachua.blog.blogserver.wrapper.ResultWrapper;
import cn.lichuachua.blog.core.support.web.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author 李歘歘
 */
@Api(value = "ArticleController", tags = {"文章API"})
@RestController
@RequestMapping(value = "/article")
public class ArticleController extends BaseController<UserInfoDTO> {

    @Autowired
    private IArticleService articleService;

    @ApiOperation("发布文章")
    @PostMapping("/publish")
    public ResultWrapper publish (
            @Valid ArticlePublishForm articlePublishForm,
            BindingResult bindingResult) {
        /**
         * 参数验证
         */
        validateParams(bindingResult);

        /**
         * 发布文章
         */
        String userId = getCurrentUserInfo().getUserId();
//        获取当前登录 的用户错误   不能获取到当前登录的用户
        articleService.publish(articlePublishForm, userId);
        return ResultWrapper.success();
    }

}
