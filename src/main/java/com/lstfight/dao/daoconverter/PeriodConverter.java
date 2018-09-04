package com.lstfight.dao.daoconverter;

import javax.persistence.AttributeConverter;
import java.time.Period;

/**
 * @author 李尚庭
 */
public class PeriodConverter implements AttributeConverter<Period, String> {
    @Override
    public String convertToDatabaseColumn(Period attribute) {
        if (null == attribute) {
            return null;
        }
        return attribute.toString();
    }

    @Override
    public Period convertToEntityAttribute(String dbData) {
        if (null == dbData) {
            return null;
        }
        return Period.parse(dbData);
    }
}
