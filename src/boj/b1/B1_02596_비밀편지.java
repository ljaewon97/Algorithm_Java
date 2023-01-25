package boj.b1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B1_02596_비밀편지 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int[][] arr = {
				{0,0,0,0,0,0},
				{0,0,1,1,1,1},
				{0,1,0,0,1,1},
				{0,1,1,1,0,0},
				{1,0,0,1,1,0},
				{1,0,1,0,0,1},
				{1,1,0,1,0,1},
				{1,1,1,0,1,0}
		};
		
		int N = Integer.parseInt(br.readLine());
		
		char[] text = br.readLine().toCharArray();
		char[] ans = new char[N];
		
		for(int i = 0; i < N; i++) {
			int cnt = 0;
			int letter = 0;
			
			for(int j = 0; j < 8; j++) {
				int diff = 0;
				
				for(int k = 0; k < 6; k++) {
					if(text[6*i+k]-'0' != arr[j][k]) diff++;
				}
				
				if(diff < 2) {
					cnt++;
					letter = j;
				}
			}
			
			if(cnt == 1) ans[i] = (char) (65+letter);
			else {
				System.out.println(i+1);
				return;
			}
		}
		
		for(int i = 0; i < N; i++) {
			sb.append(ans[i]);
		}
		
		System.out.println(ans);
	}
}
