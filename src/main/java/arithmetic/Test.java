package arithmetic;

import java.util.Scanner;

public class Test {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		int oldprice = 0, price = 0, i = 0;
		System.out.println("请先设置商品的真是价格：");
		oldprice = new Scanner(System.in).nextInt();
		System.out.println("请输入试猜的价格：\n");

		while (oldprice != price) {
			i++;
			System.out.println("参与者：");
			price = new Scanner(System.in).nextInt();
			System.out.println("主持人：");
			if (price > oldprice) {
				System.out.println("高了\n");
			} else if (price < oldprice) {
				System.out.println("低了\n");
			} else {
				System.out.println("恭喜你，答对了。该商品属于你了！\n一共猜了" + i + "次");
			}
		}
	}
}
