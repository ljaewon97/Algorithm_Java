package boj.s4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class S4_01021_회전하는_큐 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Deque<Integer> deque = new ArrayDeque<>();
		
		for(int i = 1; i <= N; i++) {
			deque.add(i);
		}
		
		int ans = 0;
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) {
			int x = Integer.parseInt(st.nextToken());
			int temp = 0;
			
			while(deque.peek() != x) {
				deque.add(deque.poll());
				temp++;
			}
			
			ans += Math.min(temp, deque.size()-temp);
			deque.poll();
		}
		
		System.out.println(ans);
	}
}
