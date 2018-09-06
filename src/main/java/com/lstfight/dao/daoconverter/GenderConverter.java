package com.lstfight.dao.daoconverter;

import com.lstfight.dao.constant.DirectConstant;

import javax.persistence.AttributeConverter;

/**
 * @author 李尚庭
 */
public class GenderConverter implements AttributeConverter<DirectConstant.Gender, Character> {
    @Override
    public Character convertToDatabaseColumn(DirectConstant.Gender attribute) {
        if (null == attribute) {
            return null;
        }
        return attribute.getGender();
    }

    @Override
    public DirectConstant.Gender convertToEntityAttribute(Character dbData) {
        if (null == dbData) {
            return null;
        }
        return DirectConstant.Gender.fromCode(dbData);
    }
}

