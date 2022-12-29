package boj.s4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S4_02960_에라토스테네스의_체 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		boolean[] check = new boolean[N+1];
		int cnt = 0;
		
		for(int i = 2; i <= N; i++) {
			if(check[i]) continue;
			cnt++;
			if(cnt == K) {
				System.out.println(i);
				return;
			}
			for(int j = 2*i; j <= N; j += i) {
				if(check[j]) continue;
				check[j] = true;
				cnt++;
				if(cnt == K) {
					System.out.println(j);
					return;
				}
			}
		}
	}
}
