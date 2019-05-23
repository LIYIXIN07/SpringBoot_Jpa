package cn.lichuachua.blog.blogserver.service.impl;

import cn.lichuachua.blog.blogserver.dto.TokenInfo;
import cn.lichuachua.blog.blogserver.entity.User;
import cn.lichuachua.blog.blogserver.enums.ErrorCodeEnum;
import cn.lichuachua.blog.blogserver.enums.UserStatusEnum;
import cn.lichuachua.blog.blogserver.exception.UserException;
import cn.lichuachua.blog.blogserver.form.UserLoginForm;
import cn.lichuachua.blog.blogserver.form.UserRegisterForm;
import cn.lichuachua.blog.blogserver.repository.redis.IRedisRepository;
import cn.lichuachua.blog.blogserver.service.IUserService;
import cn.lichuachua.blog.commom.util.NickNameUtil;
import cn.lichuachua.blog.commom.util.TokenUtil;
import cn.lichuachua.blog.core.support.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

/**
 * @author 李歘歘
 * 用户业务类接口实现类
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User,String> implements IUserService{

    @Autowired
    private IRedisRepository redisRepository;

    /**
     * 用户注册
     */
    @Override
    public void register(UserRegisterForm userRegisterForm){

        User user = new User();
        user.setMobile(userRegisterForm.getMobile());

        /**
         * 检验手机是否被注册过
         */

        Optional<User> userOptional = selectOne(Example.of(user));
        if (userOptional.isPresent()){
            throw new UserException(ErrorCodeEnum.MOBILE_REGISTERED);
        }
        /**
         * 检查两次密码是否相同
         */
        if (!userRegisterForm.getPassword().equals(userRegisterForm.getConfirmPassword())){
            throw new UserException((ErrorCodeEnum.TWO_PASSWORD_NO_EQUALS));
        }
        /**
         *  保存用户信息到数据库，完成注册
         */
        user.setPassword(userRegisterForm.getPassword());
        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());
        user.setStatus(UserStatusEnum.NORMAL.getStatus());
        user.setNickName(NickNameUtil.genNick());
        save(user);
    }

    /**
     * 用户登录
     */
    @Override
    public TokenInfo login(UserLoginForm userLoginForm) {
        /**
         * 查找用户
         */
        User user = new User();
        user.setMobile(userLoginForm.getMobile());
        Optional<User> optionalUser = selectOne(Example.of(user));
        if (!optionalUser.isPresent()){
            throw new UserException(ErrorCodeEnum.MOBILE_NOT_REGISTERED);
        }else {
            user = optionalUser.get();
        }
        /**
         * 判断密码是否正确
         */
        if (!user.getPassword().equals(userLoginForm.getPassword())){
            throw new UserException(ErrorCodeEnum.PASSWORD_ERROR);
        }
        /**
         * 生成accessToken
         */
        String accessToken = TokenUtil.genToken();

        /**
         * 将accessToken和用户信息存入Redis ，并删除旧的Token
         */
        String userId = user.getUserId();
        TokenInfo tokenInfo = new TokenInfo();
        tokenInfo.setUserId(userId);
        tokenInfo.setAccessToken(accessToken);


        //获取旧的Token并删除，---------通知客户端在其他地方登录
        String olderAccessToken = redisRepository.findAccessTokenByUserId(userId);
        if (null !=olderAccessToken){
            //TODO  通知客户端在其他地方登录
            //删除旧的Token
            redisRepository.deleteAccessToken(olderAccessToken);
        }

        //保存新的Token，更新当前用户使用的Token
        redisRepository.saveAccessToken(tokenInfo);
        redisRepository.saveUserAccessToken(userId,accessToken);


        /**
         * 返回登录结果
         */
        return  tokenInfo;
    }

}
