package com.lstfight.dao.constant;

/**
 * 数据字典常量
 * // TODO: 2018/7/29
 */
public class DirectConstant {

    public enum Gender {
        MALE('M'),FEMALE('F'),UNKNOWN('S');
        private final char gender;
        Gender(char gender) {
            this.gender = gender;
        }
        //枚举类在
        public static Gender fromCode(char code) {
            if ( code == 'M' || code == 'm' ) {
                return MALE;
            }
            if ( code == 'F' || code == 'f' ) {
                return FEMALE;
            }
            if ( code == 'S' || code == 's' ) {
                return FEMALE;
            }
            throw new UnsupportedOperationException(
                    "The code " + code + " is not supported!"
            );
        }
        //enum只有get方法
        public char getGender() {
            return gender;
        }
    }

}
