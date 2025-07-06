package com.ziqian.v1;

/**
 * @description:
 * @author: Punny
 * @date: 2025/7/6 17:48
 */
public class Order {
    private final long orderId;
    private final Integer price;
    private Integer number;
    private final Integer type;  // 1 = sell, 2 = buy

    private Order(Integer price, Integer number, Integer type) {
        this.orderId = System.currentTimeMillis();
        this.price = price;
        this.number = number;
        this.type = type;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {}

    public Integer getPrice() {
        return price;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getType() {
        return type;
    }

    // 买单构建器
    public static Builder buyOrderBuilder() {
        return new Builder(2);
    }

    // 卖单构建器
    public static Builder sellOrderBuilder() {
        return new Builder(1);
    }

    /**
     * 使用Builder模式，只能创建指定type的Order
     */
    public static class Builder {
        private Integer price;
        private Integer number;
        private final Integer type;

        private Builder(Integer type) {
            this.type = type;
        }

        public Builder price(Integer price) {
            this.price = price;
            return this;
        }

        public Builder number(Integer number) {
            this.number = number;
            return this;
        }

        public Order build() {
            if (price == null || number == null) {
                throw new IllegalStateException("Price and number must be set");
            }
            return new Order(price, number, type);
        }
    }
}

