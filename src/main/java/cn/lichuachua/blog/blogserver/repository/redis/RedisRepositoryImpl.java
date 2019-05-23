package cn.lichuachua.blog.blogserver.repository.redis;

import cn.lichuachua.blog.blogserver.dto.ClientInfo;
import cn.lichuachua.blog.blogserver.dto.TokenInfo;
import cn.lichuachua.blog.blogserver.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

import static cn.lichuachua.blog.blogserver.util.RedisKeyUtil.buildKey;
import static cn.lichuachua.blog.blogserver.constant.RedisKeyTemplate.*;


/**
 * @author 李歘歘
 */
@Component
public class RedisRepositoryImpl implements IRedisRepository {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public void saveAccessToken(TokenInfo tokenInfo) {
        RedisUtil.set(redisTemplate, buildKey(T_ACCESS_TOKEN, tokenInfo.getAccessToken()), tokenInfo);
    }

    @Override
    public void saveUserAccessToken(String userId, String accessToken) {
        RedisUtil.set(redisTemplate, buildKey(T_USER_CURRENT_TOKEN, userId), accessToken);
    }

    @Override
    public String findAccessTokenByUserId(String userId) {
        return RedisUtil.<String>get(redisTemplate, buildKey(T_USER_CURRENT_TOKEN, userId), String.class);
    }

    @Override
    public TokenInfo findTokenInfoByAccessToken(String accessToken) {
        return RedisUtil.<TokenInfo>get(redisTemplate, buildKey(T_ACCESS_TOKEN, accessToken), TokenInfo.class);
    }

    @Override
    public void deleteAccessToken(String accessTToken) {
        RedisUtil.del(redisTemplate, buildKey(T_ACCESS_TOKEN, accessTToken));
    }

    @Override
    public void deleteUserAccessToken(String userId) {
        RedisUtil.del(redisTemplate, buildKey(T_USER_CURRENT_TOKEN, userId));
    }

    @Override
    public void saveClientInfo(String userId, ClientInfo clientInfo) {
        RedisUtil.set(redisTemplate, buildKey(T_USER_CURRENT_CLIENT, userId), clientInfo);
    }

    @Override
    public void deleteClientInfo(String userId) {
        RedisUtil.del(redisTemplate, buildKey(T_USER_CURRENT_CLIENT, userId));
    }

    @Override
    public ClientInfo findClientInfoByUserId(String userId) {
        return RedisUtil.get(redisTemplate, buildKey(T_USER_CURRENT_CLIENT, userId), ClientInfo.class);
    }



}