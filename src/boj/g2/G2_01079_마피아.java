package boj.g2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G2_01079_마피아 {
	static int[][] R;
	static int[] G;
	static boolean[] dead;
	static int N, E, ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		G = new int[N];
		R = new int[N][N];
		dead = new boolean[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			G[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				R[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		E = Integer.parseInt(br.readLine());
		
		solve(0, N%2 == 0, N);
		
		System.out.println(ans);
	}
	
	static void solve(int count, boolean night, int survived) {
		if(survived == 1 || dead[E]) {
			ans = Math.max(ans, count);
			return;
		}
		
		if(night) {
			for(int i = 0; i < N; i++) {
				if(dead[i] || i == E) continue;
				
				dead[i] = true;
				for(int j = 0; j < N; j++) {
					G[j] += R[i][j];
				}
				solve(count+1, !night, survived-1);
				dead[i] = false;
				for(int j = 0; j < N; j++) {
					G[j] -= R[i][j];
				}
			}
		}
		else {
			int max = Integer.MIN_VALUE, idx = -1;
			for(int i = 0; i < N; i++) {
				if(dead[i]) continue;
				
				if(G[i] > max) {
					max = G[i];
					idx = i;
				}
			}
			
			dead[idx] = true;
			solve(count, !night, survived-1);
			dead[idx] = false;
		}
	}
}
