package codeforces.div3.round820;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class D_Friends_and_the_Restaurant {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());
		
		while(t-- > 0) {
			int n = Integer.parseInt(br.readLine());
			int[] x = new int[n];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++) {
				x[i] = Integer.parseInt(st.nextToken());
			}
			
			PriorityQueue<Integer> pos = new PriorityQueue<>();
			PriorityQueue<Integer> neg = new PriorityQueue<>();
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++) {
				int y = Integer.parseInt(st.nextToken());
				
				if(y - x[i] >= 0) pos.add(y-x[i]);
				else neg.add(x[i]-y);
			}
			
			int max = pos.size() / 2;
			int cnt = 0;
			
			while(true) {
				if(!pos.isEmpty() && !neg.isEmpty()) {
					int sum = pos.peek() - neg.peek();
					
					if(sum >= 0) {
						pos.poll();
						neg.poll();
						cnt++;
					}
					else {
						if(pos.size() >= 2) {
							pos.poll();
							pos.poll();
							cnt++;
						}
						else {
							break;
						}
					}
				}
				else break;
			}
			
			cnt += pos.size() / 2;
			
			sb.append(Math.max(max, cnt)).append("\n");
		}
		
		System.out.println(sb);
	}
}
