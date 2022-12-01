package boj.s5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S5_08979_올림픽 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		Nation[] nations = new Nation[N+1];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			nations[n] = new Nation(g, s, b);
		}
		
		int rank = 1;
		Nation n = nations[K];
		
		for(int i = 1; i <= N; i++) {
			if(i == K) continue;
			
			if(nations[i].g > n.g) {
				rank++;
			}
			else if(nations[i].g == n.g) {
				if(nations[i].s > n.s) {
					rank++;
				}
				else if(nations[i].s == n.s) {
					if(nations[i].b > n.b) {
						rank++;
					}
				}
			}
		}
		
		System.out.println(rank);
	}
	
	static class Nation {
		int g, s, b;
		
		public Nation(int g, int s, int b) {
			this.g = g;
			this.s = s;
			this.b = b;
		}
	}
}
