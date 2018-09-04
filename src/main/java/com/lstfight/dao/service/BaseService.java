package com.lstfight.dao.service;

import com.lstfight.dao.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * <p>简单业务逻辑抽象接口</p>
 *
 * @author 李尚庭
 * @date 2018/7/31 0031 9:54
 */

public interface BaseService<T extends BaseEntity, ID extends Serializable> {

    /**
     * 根据实体主键删除
     *
     * @param domainClass 实体类型
     * @param id          实体对象id
     * @return 删除结果
     */
    Boolean delete(Class<T> domainClass, ID id);

    /**
     * 保存一个实体对象
     *
     * @param entity 实体对象
     * @return 保存的实体对象
     */
    T save(T entity);

    /**
     * 根据实体类型，批量删除实体
     *
     * @param classT
     * @param keys
     * @return
     */
    Boolean deleteBatch(Class<T> classT, List<ID> keys);

    /**
     * 更新实体对象
     *
     * @param t
     * @return 返回更新后的实体对象
     */
    T update(T t);

    /**
     * 根据实体类型，主键查询实体对象
     *
     * @param classT 实体类型
     * @param key    主键值
     * @return 返回实体对象，未找到对象返回null
     */
    T findOne(Class<T> classT, Serializable key);

}
