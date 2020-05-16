package doan.stores.enums;

import lombok.Getter;

import java.util.EnumSet;

@Getter
public enum EGender {

    MALE(0, "Nam"),
    FEMALE(1, "Nữ");

    private int value;
    private String text;

    EGender(int value, String text) {
        this.value = value;
        this.text = text;
    }

    public static EGender of(int value) {
        return EnumSet.allOf(EGender.class).stream()
                .filter(eGender -> eGender.getValue() == value)
                .findFirst()
                .orElse(null);
    }

}
