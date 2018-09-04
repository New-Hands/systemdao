package com.lstfight.dao.service;


import com.lstfight.dao.entity.BaseEntity;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

/**
 * <p>简单业务逻辑抽象接口实现</p>
 *
 * @author 李尚庭
 * @date 2018/7/31 0031 10:00
 */
public class BaseServerImpl<T extends BaseEntity, ID extends Serializable> implements BaseService<T, ID> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public T save(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public T findOne(Class<T> classT, Serializable s) {
        return entityManager.find(classT, s);
    }

    @Override
    public Boolean delete(Class<T> classT, ID key) {
        try {
            entityManager.remove(entityManager.find(classT, key));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override

    public Boolean deleteBatch(Class<T> classT, List<ID> keys) {
        try {
            for (ID key : keys) {
                delete(classT, key);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public T update(T t) {
        return entityManager.merge(t);
    }

}
