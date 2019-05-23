package cn.lichuachua.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author 李歘歘
 * 运行类
 */
@EnableSwagger2
@SpringBootApplication
public class BlogServerApplication {

    public static void main(String[] args){
        SpringApplication.run(BlogServerApplication.class, args);
    }
}
