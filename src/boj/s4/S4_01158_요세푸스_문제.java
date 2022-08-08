package boj.s4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class S4_01158_요세푸스_문제 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		Deque<Integer> deque = new LinkedList<>();
		
		for(int i = 1; i <= N; i++) {
			deque.add(i);
		}
		
		int cnt = 1;
		while(!deque.isEmpty()) {
			int num = deque.poll();
			if(cnt % K == 0) {
				if(cnt == K) {
					sb.append("<").append(num);
				}
				else {
					sb.append(", ").append(num);
				}
			}
			else {
				deque.add(num);
			}
			cnt++;
		}
		sb.append(">");
		
		System.out.println(sb);
	}
}
