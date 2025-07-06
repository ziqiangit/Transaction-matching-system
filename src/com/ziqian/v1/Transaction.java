package com.ziqian.v1;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
/**
 * @description:
 * @author: Punny
 * @date: 2025/7/6 17:52
 */
public class Transaction{
    private Integer currentPrice;
    private Queue<Order> sellQueue;
    private Queue<Order> buyQueue;
    public void transact(Order order){
        if(order.getType().equals(1)){
            List<Order> temp = buyQueue.stream().toList();
            buyQueue.clear();
            for(Order o : temp){
                if(order.getNumber() > 0 && o.getPrice().equals(order.getPrice())){
                    Integer num = Math.min(o.getNumber(), order.getNumber());
                    o.setNumber(o.getNumber() - num);
                    order.setNumber(order.getNumber() - num);
                    currentPrice = order.getPrice();
                }
                if(o.getNumber() > 0){
                    buyQueue.add(o);
                }
            }
            if(order.getNumber() > 0){
                sellQueue.add(order);
            }
        }else{
            List<Order> temp = sellQueue.stream().toList();
            sellQueue.clear();
            for(Order o : temp){
                if(order.getNumber() > 0 && o.getPrice().equals(order.getPrice())){
                    Integer num = Math.min(o.getNumber(), order.getNumber());
                    o.setNumber(o.getNumber() - num);
                    order.setNumber(order.getNumber() - num);
                    currentPrice = order.getPrice();
                }
                if(o.getNumber() > 0){
                    sellQueue.add(o);
                }
            }
            if(order.getNumber() > 0){
                buyQueue.add(order);
            }
        }
        show();
    }
    public Transaction(){
        sellQueue = new PriorityQueue<>((o1,o2)->{
            if(o1.getPrice().equals(o2.getPrice())){
                return Long.compare(o1.getOrderId(), o2.getOrderId());
            }
            return o1.getPrice()-o2.getPrice();
        });
        buyQueue = new PriorityQueue<>((o1,o2)->{
            if(o1.getPrice().equals(o2.getPrice())){
                return Long.compare(o1.getOrderId(), o2.getOrderId());
            }
            return o2.getPrice()-o1.getPrice();
        });
        currentPrice = 0;
    }
    public void show() {
        System.out.println("=".repeat(40));
        System.out.println(String.format("📈 当前最新成交价：%d", currentPrice));
        System.out.println("-".repeat(40));

        System.out.println("🟩 买单队列（Buy Orders）：");
        buyQueue.stream()
                .sorted((o1, o2) -> {
                    if (o1.getPrice().equals(o2.getPrice())) {
                        return Long.compare(o1.getOrderId(), o2.getOrderId());
                    }
                    return o2.getPrice() - o1.getPrice(); // 高价优先
                })
                .forEach(order -> System.out.println(
                        String.format("  - 💰价格: %d, 📦数量: %d, 🆔订单ID: %d",
                                order.getPrice(), order.getNumber(), order.getOrderId())
                ));

        System.out.println("-".repeat(40));

        System.out.println("🟥 卖单队列（Sell Orders）：");
        sellQueue.stream()
                .sorted((o1, o2) -> {
                    if (o1.getPrice().equals(o2.getPrice())) {
                        return Long.compare(o1.getOrderId(), o2.getOrderId());
                    }
                    return o1.getPrice() - o2.getPrice(); // 低价优先
                })
                .forEach(order -> System.out.println(
                        String.format("  - 💰价格: %d, 📦数量: %d, 🆔订单ID: %d",
                                order.getPrice(), order.getNumber(), order.getOrderId())
                ));

        System.out.println("=".repeat(40));
        System.out.println();
    }

}
