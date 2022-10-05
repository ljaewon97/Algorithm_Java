package boj.s1;

import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;

public class S1_06064_카잉_달력 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			boolean flag = false;
			
			for(int i = x; i <= M*N; i += M) {
				if((i-1) % N + 1 == y) {
					sb.append(i).append("\n");
					flag = true;
					break;
				}
			}	
			
			if(!flag) sb.append(-1).append("\n");
		}
		
		System.out.println(sb);
	}
}
