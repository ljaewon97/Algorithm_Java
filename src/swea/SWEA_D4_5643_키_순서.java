package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_D4_5643_키_순서 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		final int INF = 1000000000;
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			int M = Integer.parseInt(br.readLine());
			
			int[][] graph = new int[N+1][N+1];
			
			while(M-- > 0) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				graph[a][b] = 1;
			}
			
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					if(i != j && graph[i][j] == 0) graph[i][j] = INF;
				}
			}
			
			for(int k = 1; k <= N; k++) {
				for(int i = 1; i <= N; i++) {
					for(int j = 1; j <= N; j++) {
						graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
					}
				}
			}
			
			int ans = 0;
			
			for(int i = 1; i <= N; i++) {
				boolean[] check = new boolean[N+1];
				boolean flag = true;
				
				for(int j = 1; j <= N; j++) {
					if(graph[i][j] != INF) check[j] = true;
					if(graph[j][i] != INF) check[j] = true;
				}
				
				for(int j = 1; j <= N; j++) {
					if(!check[j]) {
						flag = false;
						break;
					}
				}
				
				if(flag) ans++;
			}
			
			sb.append(String.format("#%d %d\n", t, ans));
		}
		
		System.out.println(sb);
	}
}
