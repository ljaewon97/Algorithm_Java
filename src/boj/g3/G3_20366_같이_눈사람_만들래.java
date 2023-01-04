package boj.g3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class G3_20366_같이_눈사람_만들래 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		int ans = Integer.MAX_VALUE;
		
		for(int i = 0; i < N-3; i++) {
			for(int j = i+3; j < N; j++) {
				int s1 = arr[i] + arr[j];
				int l = i+1;
				int r = j-1;
				int s2 = arr[l] + arr[r];
				ans = Math.min(ans, Math.abs(s2-s1));
				
				while(l < r) {
					if(s2 > s1) {
						s2 = arr[l] + arr[--r];
						ans = Math.min(ans, Math.abs(s2-s1));
					}
					else if(s1 == s2) {
						System.out.println(0);
						return;
					}
					else {
						s2 = arr[++l] + arr[r];
						ans = Math.min(ans, Math.abs(s2-s1));
					}
				}
			}
		}
		
		System.out.println(ans);
	}
}
