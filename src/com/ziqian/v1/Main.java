package com.ziqian.v1;

/**
 * @description:
 * @author: Punny
 * @date: 2025/7/6 17:46
 */

import java.util.Scanner;

/**
 * 用户交互主程序
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Transaction transaction = new Transaction();

        System.out.println("=== 欢迎使用简易交易撮合系统 ===");

        while (true) {
            System.out.println();
            System.out.println("请选择操作：");
            System.out.println("1. 创建买单");
            System.out.println("2. 创建卖单");
            System.out.println("3. 查看交易看板");
            System.out.println("0. 退出系统");
            System.out.print("输入选项：");

            String input = scanner.nextLine().trim();
            switch (input) {
                case "1" -> {
                    Order order = createOrder(scanner, true);
                    transaction.transact(order);
                }
                case "2" -> {
                    Order order = createOrder(scanner, false);
                    transaction.transact(order);
                }
                case "3" -> transaction.show(); // 可设为 public
                case "0" -> {
                    System.out.println("再见！");
                    return;
                }
                default -> System.out.println("❌ 无效选项，请重新输入。");
            }
        }
    }

    private static Order createOrder(Scanner scanner, boolean isBuy) {
        System.out.print("请输入价格（整数）：");
        int price = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("请输入数量（整数）：");
        int number = Integer.parseInt(scanner.nextLine().trim());

        Order.Builder builder = isBuy ? Order.buyOrderBuilder() : Order.sellOrderBuilder();
        return builder.price(price).number(number).build();
    }
}
