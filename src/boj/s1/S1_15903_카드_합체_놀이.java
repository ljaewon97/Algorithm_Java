package boj.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class S1_15903_카드_합체_놀이 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Long> pq = new PriorityQueue<>();
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			pq.add(Long.parseLong(st.nextToken()));
		}
		
		while(m-- > 0) {
			long x = pq.poll();
			long y = pq.poll();
			
			pq.add(x+y);
			pq.add(x+y);
		}
		
		long ans = 0;
		
		for(long num: pq) {
			ans += num;
		}
		
		System.out.println(ans);
	}
}
