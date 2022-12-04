package boj.s5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S5_01969_DNA {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] count = new int[M][4];
		
		for(int j = 0; j < N; j++) {
			String line = br.readLine();
			
			for(int i = 0; i < M; i++) {
				char c = line.charAt(i);
				
				if(c == 'A') count[i][0]++;
				else if(c == 'C') count[i][1]++;
				else if(c == 'G') count[i][2]++;
				else if(c == 'T') count[i][3]++;
			}
		}
		
		char[] ans = new char[M];
		int sum = 0;
		
		for(int i = 0; i < M; i++) {
			int max = 0, idx = 0;
			
			for(int j = 0; j < 4; j++) {
				if(count[i][j] > max) {
					max = count[i][j];
					idx = j;
				}
			}
			
			if(idx == 0) ans[i] = 'A';
			else if(idx == 1) ans[i] = 'C';
			else if(idx == 2) ans[i] = 'G';
			else if(idx == 3) ans[i] = 'T';
			
			sum += N - max;
		}
		
		System.out.println(new String(ans));
		System.out.println(sum);
	}
}
