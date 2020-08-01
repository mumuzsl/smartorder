package com.cqjtu.smartorder.global;

/**
 * @author mumu
 * @date 2020/7/26
 */
public enum RoleEnum {

    //
    ADMIN(1, "ADMIN"),

    //
    WAITER(2, "WAITER"),

    //
    CHEF(3, "CHEF");

    private int value;
    private String name;

    RoleEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static RoleEnum valueOf(int value) {
        return values()[value - 1];
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
