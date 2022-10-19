package boj.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S2_18442_우체국_1 {
	static long[] locs, answer;
	static boolean[] po;
	static long S = Long.MAX_VALUE;
	static int V, P;
	static long L;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		L = Long.parseLong(st.nextToken());
		
		locs = new long[V];
		answer = new long[P];
		po = new boolean[V];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < V; i++) {
			locs[i] = Long.parseLong(st.nextToken());
		}
		
		solve(0, 0);
		
		sb.append(S).append("\n");
		
		for(int i = 0; i < P; i++) {
			sb.append(answer[i]).append(" ");
		}
		
		System.out.println(sb);
	}
	
	static void solve(int nth, int start) {
		if(nth == P) {
			long sum = 0;
			
			for(int i = 0; i < V; i++) {
				if(po[i]) continue;
				
				int l = i, r = i;
				
				while(!po[l]) {
					l--;
					if(l < 0) l = V-1;
				}
				
				while(!po[r]) {
					r++;
					if(r > V-1) r = 0;
				}
				
				sum += Math.min(Math.min(Math.abs(locs[i]-locs[l]), L-Math.abs(locs[i]-locs[l])), Math.min(Math.abs(locs[i]-locs[r]), L-Math.abs(locs[i]-locs[r])));
			}
			
			if(sum < S) {
				S = sum;
				int idx = 0;
				for(int i = 0; i < V; i++) {
					if(po[i]) answer[idx++] = locs[i];
				}
			}
			return;
		}
		
		for(int i = start; i < V; i++) {
			po[i] = true;
			solve(nth+1, i+1);
			po[i] = false;
		}
	}
}
