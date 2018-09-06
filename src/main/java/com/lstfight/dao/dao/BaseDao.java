package com.lstfight.dao.dao;

import com.lstfight.dao.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 不需要创建中间实例
 *
 * @param <T>  baseEntity类型实体
 * @param <ID> 序列化类型的ID
 * @author 李尚庭
 * @version 1.0
 * @date 2018/7/26 0026 11:21
 */
@NoRepositoryBean
public interface BaseDao<T extends BaseEntity, ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

    /**
     * 根据动态sql查询指定实体list
     *
     * @param sql         动态sql
     * @param params      参数map
     * @param domainClass 实体类
     * @return 指定实体类list 当没有匹配实体时 返回一个size为0的list
     */
    List<T> queryListBySql(String sql, Map<String, Object> params, Class<T> domainClass);

    /**
     * @param sql         动态sql
     * @param start       开始位置
     * @param length      偏移量
     * @param params      查询条件
     * @param domainClass 实体
     * @return 指定实体类list 当没有匹配实体时 返回一个size为0的list
     */
    List<T> queryListBySql(String sql, int start, int length, Map<String, Object> params, Class<T> domainClass);
}
