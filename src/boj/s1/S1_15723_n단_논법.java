package boj.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class S1_15723_n단_논법 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		
		int[][] fw = new int[26][26];
		int INF = 1000000;
		
		for(int i = 0; i < 26; i++) {
			Arrays.fill(fw[i], INF);
		}
		
		while(n-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = st.nextToken().charAt(0) - 'a';
			st.nextToken();
			int b = st.nextToken().charAt(0) - 'a';
			
			fw[a][b] = 1;
		}
		
		for(int k = 0; k < 26; k++) {
			for(int i = 0; i < 26; i++) {
				for(int j = 0; j < 26; j++) {
					fw[i][j] = Math.min(fw[i][j], fw[i][k] + fw[k][j]);
				}
			}
		}
		
		int m = Integer.parseInt(br.readLine());
		
		while(m-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = st.nextToken().charAt(0) - 'a';
			st.nextToken();
			int b = st.nextToken().charAt(0) - 'a';
			
			if(fw[a][b] != INF) sb.append("T\n");
			else sb.append("F\n");
		}
		
		System.out.println(sb);
	}
}
