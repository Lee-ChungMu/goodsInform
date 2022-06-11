package leechungmu.goodsInform.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Locale;

public enum Type {
    ENTERPRISE,
    NORMAL;

    @JsonCreator
    public static Type from(String s){
        return Type.valueOf(s.toUpperCase());
    }
}
