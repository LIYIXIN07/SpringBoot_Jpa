package cn.lichuachua.blog.blogserver.repository;

import cn.lichuachua.blog.blogserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 李歘歘
 */
@Repository
public interface UserRepository extends JpaRepository<User, String>{
}
