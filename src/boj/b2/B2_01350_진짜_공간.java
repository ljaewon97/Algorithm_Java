package boj.b2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B2_01350_진짜_공간 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		int[] files = new int[N];
		
		long cnt = 0;
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			files[i] = Integer.parseInt(st.nextToken());
		}
		
		long c = Long.parseLong(br.readLine());
		
		for(int i = 0; i < N; i++) {
			if(files[i] == 0) continue;
			cnt += (files[i]-1) / c + 1;
		}
		
		System.out.println(c * cnt);
	}
}
