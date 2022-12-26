package boj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G4_01956_운동 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		final int INF = 1000000000;
		
		st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		int[][] fw = new int[V+1][V+1];
		
		for(int i = 1; i <= V; i++) {
			for(int j = 1; j <= V; j++) {
				if(i != j) fw[i][j] = INF;
			}
		}
		
		while(E-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			fw[a][b] = c;
		}
		
		for(int k = 1; k <= V; k++) {
			for(int i = 1; i <= V; i++) {
				for(int j = 1; j <= V; j++) {
					fw[i][j] = Math.min(fw[i][j], fw[i][k] + fw[k][j]);
				}
			}
		}
		
		int ans = Integer.MAX_VALUE;
		
		for(int i = 1; i <= V; i++) {
			for(int j = i+1; j <= V; j++) {
				if(fw[i][j] != INF && fw[j][i] != INF) ans = Math.min(ans, fw[i][j] + fw[j][i]);
			}
		}
		
		if(ans == Integer.MAX_VALUE) ans = -1;
		System.out.println(ans);
	}
}
