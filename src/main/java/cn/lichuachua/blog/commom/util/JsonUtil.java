package cn.lichuachua.blog.commom.util;

import cn.lichuachua.blog.commom.enums.BaseErrorCodeEnum;
import cn.lichuachua.blog.core.exception.SysException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author 李歘歘
 * Json数据装换工具
 */

public class JsonUtil {
    /**
     * 对象装换为Json
     */
    public static String toJson(Object o){
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            return o instanceof String ? o.toString() : objectMapper.writeValueAsString(o);
        }catch (Exception e){
            e.printStackTrace();
            throw new SysException(BaseErrorCodeEnum.JSON_TRANS_ERROR);
        }
    }

    /**
     * JSON转化为对象
     */
    public static<T> T toObject(String json,Class<T> clazz){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return clazz.equals(String.class) ? (T) json : objectMapper.readValue(json, clazz);
        }catch (Exception e){
            e.printStackTrace();
            throw new SysException(BaseErrorCodeEnum.JSON_TRANS_ERROR);
        }
    }
}
