package com.lstfight.dao.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 记录登录日志
 * 数据库分布式
 *
 * @author 李尚庭
 * @date 2018/7/25 0025 17:31
 */
@Entity(name = "login_log")
@Table(name = "login_log")
public class LoginLogEntity {
    @Id
    Long id;
}
