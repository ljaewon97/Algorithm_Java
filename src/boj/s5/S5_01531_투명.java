package boj.s5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S5_01531_투명 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] paper = new int[101][101];
		
		while(N-- > 0) {
			st = new StringTokenizer(br.readLine());
			int r1 = Integer.parseInt(st.nextToken()) - 1;
			int c1 = Integer.parseInt(st.nextToken()) - 1;
			int r2 = Integer.parseInt(st.nextToken()) - 1;
			int c2 = Integer.parseInt(st.nextToken()) - 1;
			
			for(int r = r1; r <= r2; r++) {
				paper[r][c1]++;
				paper[r][c2+1]--;
			}
		}
		
		int ans = 0;
		
		for(int r = 0; r < 100; r++) {
			if(paper[r][0] > M) ans++;
			for(int c = 1; c < 100; c++) {
				paper[r][c] += paper[r][c-1];
				if(paper[r][c] > M) ans++;
			}
		}
		
		System.out.println(ans);
	}
}
