package cn.lichuachua.blog.blogserver.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author 李歘歘
 */
@Data
public class ArticlePublishForm {

    /**
     * 文章标题
     */
    @NotEmpty(message = "请填写文章标题")
    private String title;

    /**
     * 文章内容
     */
    @NotEmpty(message = "请填写文章内容")
    private String content;

    /**
     * 照片
     */
    private String photos;
}
