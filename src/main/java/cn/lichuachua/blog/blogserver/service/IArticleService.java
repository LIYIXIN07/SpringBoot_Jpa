package cn.lichuachua.blog.blogserver.service;

import cn.lichuachua.blog.blogserver.entity.Article;
import cn.lichuachua.blog.blogserver.form.ArticlePublishForm;
import cn.lichuachua.blog.core.support.service.IBaseService;

import javax.validation.Valid;

/***
 * @author 李歘歘
 */

public interface IArticleService extends IBaseService<Article, String> {

    /**
     * 发布文章
     * @param articlePublishForm
     * @param userId
     */
    void publish(@Valid ArticlePublishForm articlePublishForm, String userId);
}
