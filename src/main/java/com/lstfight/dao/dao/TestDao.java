package com.lstfight.dao.dao;

import com.lstfight.dao.entity.TestEntity;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.time.Period;

/**
 * DAO测试 该接口直接使用BaseDaoImpl中方法
 * 具体存储类实例采用factory的方式进行生成
 *
 * @author 李尚庭
 * @version 1.0
 * @date 2018/7/26 0026 11:20
 */

public interface TestDao extends BaseDao<TestEntity, Serializable> {
    /**
     * 根据period字段删除
     * 删除方法需要事务管理
     *
     * @param period 测试方法
     * @return 删除条数
     */
    Long deleteTestEntityByByzd(Period period);

    Long deleteAllById(Integer id);

    /**
     * 根据ID查找实体
     *
     * @param id
     * @return
     */
    TestEntity findById(Integer id);
}
