package boj.b3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B3_26145_출제비_재분배 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] money = new int[N+M];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			money[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < N+M; j++) {
				int x = Integer.parseInt(st.nextToken());
				
				money[i] -= x;
				money[j] += x;
			}
		}
		
		for(int i = 0; i < N+M; i++) {
			sb.append(money[i]).append(" ");
		}
		
		System.out.println(sb);
	}
}
