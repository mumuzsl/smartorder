package com.cqjtu.smartorder.global;

/**
 * @author mumu
 * @date 2020/7/26
 */
public enum OrderStatusEnum {

    //
    UNFINISHED(0),

    //
    FINISH(1);

    private int value;

    OrderStatusEnum(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }

    public static OrderStatusEnum valueOf(int i) {
        return (i < 0 || i >= values().length) ? null : values()[i];
    }
}
