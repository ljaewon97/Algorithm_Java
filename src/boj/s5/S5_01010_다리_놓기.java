package boj.s5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S5_01010_다리_놓기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			long up = 1L, down = 1L;   // nCm = (n x n-1 x ... x n-m+1) / m! = up / down
			
			if(n - m < m) {   // nCm = nCn-m
				m = n - m;
			}
			
			for(int i = 0; i < m; i++) {
				int upNum = n - i;		// 분자에 곱할 숫자
				int downNum = i + 1;	// 분모에 곱할 숫자
				
				up *= upNum;
				if(up % downNum == 0) {	// 약분
					up /= downNum;
				}
				else {
					down *= downNum;
				}
			}
			
			sb.append(up / down).append("\n");
		}
		
		System.out.println(sb);
	}
}
