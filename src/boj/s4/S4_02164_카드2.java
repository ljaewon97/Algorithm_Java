package boj.s4;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class S4_02164_카드2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Deque<Integer> deque = new ArrayDeque<>();
		
		int N = sc.nextInt();
		for(int i = 1; i <= N; i++) {
			deque.add(i);
		}
		
		while(deque.size() != 1) {
			deque.poll();
			deque.add(deque.poll());
		}
		
		System.out.println(deque.poll());
		sc.close();
	}
}
