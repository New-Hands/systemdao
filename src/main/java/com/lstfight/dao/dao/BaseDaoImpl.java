package com.lstfight.dao.dao;

import com.lstfight.dao.entity.BaseEntity;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 实现仓储方法 共用存储层
 * repository baseClass
 *
 * @param <T>  实体类型
 * @param <ID>
 * @author 李尚庭
 * @date 2018/7/25 0025 15:49
 */
@NoRepositoryBean
public class BaseDaoImpl<T extends BaseEntity, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseDao<T, ID> {

    /**
     * 实体管理器 用于实现自定义语句实现
     */
    EntityManager entityManager;

    public BaseDaoImpl(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
        this.entityManager = em;
    }

    @Override
    public List<T> queryListBySql(String sql, Map<String, Object> params, Class<T> domainClass) {
        Query query = entityManager.createNativeQuery(sql, domainClass);
        setParameter(query, params);
        return (List<T>)query.getResultList();
    }

    @Override
    public List<T> queryListBySql(String sql, int start, int length,
                                  Map<String, Object> params, Class<T> domainClass) {
        Query query = entityManager.createNativeQuery(sql, domainClass);
        setParameter(query, params);
        setPage(query, start, length);
        return (List<T>)query.getResultList();
    }

    private void setParameter(Query query, Map<String, Object> params){
        if (params != null) {
            Iterator<Map.Entry<String, Object>> it = params.entrySet().iterator();
            Map.Entry<String, Object> en;
            while (it.hasNext()) {
                en = it.next();
                query.setParameter(en.getKey(), en.getValue());
            }
        }
    }

    private void setPage(Query query, int start, int length){
        if (length > 0) {
            query.setFirstResult(start);
            query.setMaxResults(length);
        }
    }
}
