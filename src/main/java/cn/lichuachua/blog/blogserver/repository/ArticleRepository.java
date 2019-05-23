package cn.lichuachua.blog.blogserver.repository;

import cn.lichuachua.blog.blogserver.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 李歘歘
 */
@Repository
public interface ArticleRepository extends JpaRepository<Article, String> {
}
