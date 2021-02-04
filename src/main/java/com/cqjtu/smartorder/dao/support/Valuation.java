package com.cqjtu.smartorder.dao.support;

/**
 * @author mumu
 * @date 2020/11/9
 */
public class Valuation implements Valuable {

    private Integer count;
    private Float price;

    private Valuation(Integer count, Float price) {
        this.count = count;
        this.price = price;
    }

    @Override
    public Integer count() {
        return count;
    }

    @Override
    public Float price() {
        return price;
    }

    public static Valuable of(Integer count, Float price) {
        return new Valuation(count, price);
    }
}
