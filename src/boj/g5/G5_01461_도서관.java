package boj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class G5_01461_도서관 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int ans = 0;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Integer> pq1 = new PriorityQueue<>(Collections.reverseOrder());
		PriorityQueue<Integer> pq2 = new PriorityQueue<>(Collections.reverseOrder());
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int loc = Integer.parseInt(st.nextToken());
			
			if(loc < 0) pq1.add(-loc);
			else pq2.add(loc);
		}
		
		boolean first = true;
		while(!pq1.isEmpty() && !pq2.isEmpty()) {
			if(pq1.peek() > pq2.peek()) {
				if(first) {
					ans += pq1.peek();
					first = false;
				}
				else ans += pq1.peek() * 2;
				
				for(int i = 0; i < M; i++) {
					if(pq1.isEmpty()) break;
					pq1.poll();
				}
			}
			else {
				if(first) {
					ans += pq2.peek();
					first = false;
				}
				else ans += pq2.peek() * 2;
				
				for(int i = 0; i < M; i++) {
					if(pq2.isEmpty()) break;
					pq2.poll();
				}
			}
		}
		
		while(!pq1.isEmpty()) {
			if(first) {
				ans += pq1.peek();
				first = false;
			}
			else ans += pq1.peek() * 2;
			
			for(int i = 0; i < M; i++) {
				if(pq1.isEmpty()) break;
				pq1.poll();
			}
		}
		
		while(!pq2.isEmpty()) {
			if(first) {
				ans += pq2.peek();
				first = false;
			}
			else ans += pq2.peek() * 2;
			
			for(int i = 0; i < M; i++) {
				if(pq2.isEmpty()) break;
				pq2.poll();
			}
		}
		
		System.out.println(ans);
	}
}
