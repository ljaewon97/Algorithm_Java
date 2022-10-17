package boj.b2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B2_20299_3대_측정 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int cnt = 0;
		
		while(N-- > 0) {
			int sum = 0;
			
			int[] abils = new int[3];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < 3; i++) {
				abils[i] = Integer.parseInt(st.nextToken());
				sum += abils[i];
			}
			
			if(abils[0] < M || abils[1] < M || abils[2] < M || sum < S) continue;
			
			for(int abil: abils) {
				sb.append(abil).append(" ");
			}
			
			cnt++;
		}
		
		sb.insert(0, String.format("%d\n", cnt));
		
		System.out.println(sb);
	}
}