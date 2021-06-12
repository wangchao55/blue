package com.sky.cold.common.handler;


import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;

/**
 * 全局字段自动填充类
 * <p>一些常用的字段如create_time可以实现自动填充
 * 需要在bean中对应的字段加入相应的注解</p>
 *
 * @author wangchao
 * @date 2021-05-29 10:40 上午
 * @see <a href="https://mp.baomidou.com/guide/auto-fill-metainfo.html">网页文档</a>
 */
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {
    
    /**
     * insert时需要填充的字段
     * 需要在变量上注解：
     * <p><b>示例：</b>
     * <pre>{@code @TableField(value = "create_time",fill = FieldFill.INSERT)}</pre>
     * <pre>{@code @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)}</pre></p>
     *
     * @param metaObject 自动传入
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        setInsertFieldValByName("createTime", LocalDateTime.now(),metaObject);
        setInsertFieldValByName("updateTime", LocalDateTime.now(),metaObject);
        setInsertFieldValByName("updatetime", LocalDateTime.now(),metaObject);
    }
    
    /**
     * update时需要填充的字段
     * 需要在变量上注解：
     * <p><b>示例：</b>
     * <pre>{@code @TableField(value = "create_time",fill = FieldFill.UPDATE)}</pre></p>
     *
     * @param metaObject 自动传入
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        setUpdateFieldValByName("updatetime", LocalDateTime.now(),metaObject);
        setUpdateFieldValByName("updateTime", LocalDateTime.now(),metaObject);
    }
}
