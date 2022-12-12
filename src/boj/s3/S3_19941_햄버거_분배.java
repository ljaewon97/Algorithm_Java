package boj.s3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S3_19941_햄버거_분배 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		char[] table = br.readLine().toCharArray();
		boolean[] eaten = new boolean[N];
		int ans = 0;
		
		for(int i = 0; i < N; i++) {
			if(table[i] == 'P') {
				for(int j = Math.max(i-M, 0); j < Math.min(i+M+1, N); j++) {
					if(table[j] == 'H' && !eaten[j]) {
						ans++;
						eaten[j] = true;
						break;
					}
				}
			}
		}
		
		System.out.println(ans);
	}
}
