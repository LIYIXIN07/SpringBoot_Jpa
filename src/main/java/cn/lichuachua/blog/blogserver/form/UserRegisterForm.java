package cn.lichuachua.blog.blogserver.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author 李歘歘
 */
@Data
public class UserRegisterForm {
    @NotEmpty(message = "请填写手机号")
    private String mobile;

    @NotEmpty(message = "请填写密码")
    private String password;

    @NotEmpty(message = "再次确认密码")
    private String confirmPassword;
}
