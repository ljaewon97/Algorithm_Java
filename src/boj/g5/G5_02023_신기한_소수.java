package boj.g5;

import java.util.Scanner;

public class G5_02023_신기한_소수 {
	static int lower, upper;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		lower = (int) Math.pow(10, N-1);
		upper = (int) Math.pow(10, N);
		
		recur(2);
		recur(3);
		recur(5);
		recur(7);
		
		System.out.println(sb);
		sc.close();
	}
	
	static void recur(int n) {
		boolean prime = true;
		for(int i = 2; i < (int) Math.sqrt(n) + 1; i++) {
			if(n % i == 0) {
				prime = false;
				break;
			}
		}
		
		if(prime && lower <= n && n < upper) {
			sb.append(n).append("\n");
		}
		else if(prime) {
			int temp = n * 10;
			for(int i = temp+1; i < temp+10; i += 2) {
				if(i != 5) {
					recur(i);
				}
			}
		}
		else {
			return;
		}
		
	}
}
