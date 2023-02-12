package boj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class G4_02224_명제_증명 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		final int INF = 1000000;
		int N = Integer.parseInt(br.readLine());
		
		int[][] fw = new int[52][52];
		
		for(int i = 0; i < 52; ++i) {
			Arrays.fill(fw[i], INF);
			fw[i][i] = 0;
		}
		
		while(N-- > 0) {
			st = new StringTokenizer(br.readLine());
			int from = st.nextToken().charAt(0);
			from = from < 97 ? from-65 : from-71;
			st.nextToken();
			int to = st.nextToken().charAt(0);
			to = to < 97 ? to-65 : to-71;
			
			fw[from][to] = 1;
		}
		
		for(int k = 0; k < 52; ++k) {
			for(int i = 0; i < 52; ++i) {
				for(int j = 0; j < 52; ++j) {
					fw[i][j] = Math.min(fw[i][j], fw[i][k] + fw[k][j]);
				}
			}
		}
		
		int cnt = 0;
		
		for(int i = 0; i < 52; ++i) {
			for(int j = 0; j < 52; ++j) {
				if(i == j) continue;
				
				if(fw[i][j] != INF) {
					++cnt;
					
					char from = i < 26 ? (char)(i+65) : (char)(i+71);
					char to = j < 26 ? (char)(j+65) : (char)(j+71);
					
					sb.append(from).append(" => ").append(to).append("\n");
				}
			}
		}
		
		System.out.println(cnt);
		System.out.println(sb);
	}
}
