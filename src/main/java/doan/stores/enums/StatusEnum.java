package doan.stores.enums;

import lombok.Getter;

import java.util.EnumSet;

@Getter
public enum StatusEnum {
    CART(0, "CART"),
    PAYED(1, "PAYED"),
    SUCCESS(2, "SUCCESS"),
    PEND(3, "PEND");


    private int code;

    private String text;

    StatusEnum(int code, String text) {
        this.code = code;
        this.text = text;
    }

    public static StatusEnum ofCode(int code) {
        return EnumSet.allOf(StatusEnum.class).stream()
                .filter(roleEnum -> roleEnum.getCode() == code)
                .findFirst()
                .orElse(null);
    }

    public static StatusEnum ofText(String text) {
        return EnumSet.allOf(StatusEnum.class).stream()
                .filter(roleEnum -> roleEnum.getText().equals(text))
                .findFirst()
                .orElse(null);
    }

}
