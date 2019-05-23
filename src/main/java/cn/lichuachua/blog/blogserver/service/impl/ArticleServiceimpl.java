package cn.lichuachua.blog.blogserver.service.impl;

import cn.lichuachua.blog.blogserver.entity.Article;
import cn.lichuachua.blog.blogserver.form.ArticlePublishForm;
import cn.lichuachua.blog.blogserver.service.IArticleService;
import cn.lichuachua.blog.core.support.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author 李歘歘
 */
@Service
public class ArticleServiceimpl extends BaseServiceImpl<Article,String> implements IArticleService{

    /**
     * 发布文章
     */
    @Override
    public void publish (ArticlePublishForm articlePublishForm, String userId){

        Article article = new Article();
        article.setUserId(userId);
        article.setContent(articlePublishForm.getContent());
        article.setPhotos(articlePublishForm.getPhotos());
        article.setStatus(0);
        article.setTitle(articlePublishForm.getTitle());
        article.setCreatedAt(new Date());
        article.setUpdatedAt(new Date());
        save(article);
    }
}
