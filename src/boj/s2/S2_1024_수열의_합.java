package boj.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S2_1024_수열의_합 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		for(int i = L; i*(i-1)/2 <= N && i <= 100; i++) {
			int x = N - i*(i-1)/2;
			if(x % i == 0) {
				int s = x / i;
				for(int j = s; j < s+i; j++) {
					sb.append(j).append(" ");
				}
				System.out.println(sb);
				return;
			}
		}
		
		System.out.println(-1);
	}
}
