package com.lstfight.dao.entity;

import com.lstfight.dao.daoconverter.GenderConverter;
import com.lstfight.dao.daoconverter.PeriodConverter;
import com.lstfight.dao.constant.DirectConstant;

import javax.persistence.*;
import java.time.Period;
import java.util.Date;

/**
 * @date 2018/7/31 0031 9:10
 * @author 李尚庭
 */
@Entity
@Table(name = "test")
public class TestEntity extends BaseEntity{
    /**
     * 每个实体必须有一个ID字段 否则Hibernate会报错
     * 文档中都是使用的基本类型的包装类型
     */
    @Id
    @Column(name = "id")
    private Integer id;

    /**
     * 测试日期类型存储
     */
    @Temporal(value = TemporalType.DATE)
    @Column(name = "birth")
    private Date birth;

    /**
     * 测试二进制类型数据存储
     */
    @Lob
    @Column(name = "data")
    private Byte[] data;

    /**
     * 测试枚举类型存储和Converter的使用
     */
    @Convert(converter = GenderConverter.class)
    @Column(name = "gender")
    private DirectConstant.Gender gender;

    /**
     * converter
     */
    @Convert(converter = PeriodConverter.class)
    @Column(name = "byzd")
    private Period byzd;

    public TestEntity() {}

    public TestEntity(int id, Date birth, Byte[] data, DirectConstant.Gender gender, Period byzd) {
        this.id = id;
        this.birth = (Date) birth.clone();
        this.data = data.clone();
        this.gender = gender;
        this.byzd = byzd;
    }

    public Date getBirth() {
        return (Date) birth.clone();
    }

    public void setBirth(Date birth) {
        this.birth = (Date)birth.clone();
    }

    public Byte[] getData() {
        return data.clone();
    }

    public void setData(Byte[] data) {
        this.data = data.clone();
    }

    public DirectConstant.Gender getGender() {
        return gender;
    }

    public void setGender(DirectConstant.Gender gender) {
        this.gender = gender;
    }

    public Period getByzd() {
        return byzd;
    }

    public void setByzd(Period byzd) {
        this.byzd = byzd;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}
