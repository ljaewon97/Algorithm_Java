package codeforces.div3.round828;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class D {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int[] memo = new int[200001];
		
		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			int n = Integer.parseInt(br.readLine());
			int cnt = 0;
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++) {
				int num = Integer.parseInt(st.nextToken());
				
				while(num % 2 == 0) {
					cnt++;
					num /= 2;
				}
			}
			
			if(cnt >= n) {
				sb.append("0\n");
				continue;
			}
			
			PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
			
			for(int i = 1; i <= n; i++) {
				if(memo[i] != 0) {
					pq.add(memo[i]);
					continue;
				}
				
				int temp = 0;
				int num = i;
				
				while(num % 2 == 0) {
					temp++;
					num /= 2;
				}
				
				pq.add(temp);
				memo[i] = temp;
			}
			
			int ans = 0;
			
			while(!pq.isEmpty()) {
				cnt += pq.poll();
				ans++;
				if(cnt >= n) break;
			}
			
			if(cnt >= n) {
				sb.append(ans).append("\n");
			}
			else {
				sb.append("-1\n");
			}
		}
		
		System.out.println(sb);
	}
}
