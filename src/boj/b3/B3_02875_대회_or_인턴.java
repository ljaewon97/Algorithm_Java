package boj.b3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B3_02875_대회_or_인턴 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int ans = Math.min(N/2, M);
		
		int f = N - 2*ans;
		int m = M - ans;
		
		if(f+m < K) ans -= (K-f-m-1)/3 + 1;
		
		System.out.println(ans);
	}
}
