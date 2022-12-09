package boj.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S1_25916_싫은데요 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int i = 0, v = 0, ans = 0;
		
		for(int j = 0; j < N; j++) {
			v += arr[j];
			
			if(v <= M) ans = Math.max(ans, v);
			else {
				while(i <= j && v > M) {
					v -= arr[i];
					i++;
				}
				
				ans = Math.max(ans, v);
			}
		}
		
		System.out.println(ans);
	}
}
