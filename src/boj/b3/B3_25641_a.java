package boj.b3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class B3_25641_a {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		String S = br.readLine();
		
		Deque<Character> deque = new LinkedList<>();
		int s = 0, t = 0;
		
		for(int i = 0; i < N; i++) {
			deque.add(S.charAt(i));
			if(S.charAt(i) == 's') s++;
			else t++;
		}
		
		while(!deque.isEmpty()) {
			if(s == t) break;
			
			char c = deque.poll();
			if(c == 's') s--;
			else t--;
		}
		
		while(!deque.isEmpty()) {
			sb.append(deque.poll());
		}
		
		System.out.println(sb);
	}
}
