package boj.b1;

import java.util.Scanner;

public class B1_01110_더하기_사이클 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int ans = n;
		int cnt = 0;
		int newNum = 0;
		
		if(n == 0) {
			System.out.println(1);
			sc.close();
			return;
		}
		
		while(ans != newNum) {
			int temp = n / 10 + n % 10;
			newNum = (n % 10) * 10 + temp % 10;
			
			cnt++;
			n = newNum;
		}
		
		System.out.println(cnt);
		sc.close();
	}
}
