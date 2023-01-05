package boj.b3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B3_09094_수학적_호기심 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			int cnt = 0;
			
			for(int a = 1; a < n; a++) {
				for(int b = a+1; b < n; b++) {
					if((a*a+b*b+m) % (a*b) == 0) cnt++;
				}
			}
			
			sb.append(cnt).append("\n");
		}
		
		System.out.println(sb);
	}
}
