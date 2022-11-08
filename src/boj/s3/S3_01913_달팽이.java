package boj.s3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S3_01913_달팽이 {
	static int[][] arr;
	static int N, ar, ac, sr, sc, d, num = 1;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		int T = Integer.parseInt(br.readLine());
		
		arr = new int[N][N];
		
		sr = sc = N/2;
		arr[sr][sc] = num;
		
		for(int i = 1; i <= N-1; i++) {
			for(int j = 0; j < i; j++) {
				sr += dr[d];
				sc += dc[d];
				arr[sr][sc] = ++num;
			}
			d = (d+1)%4;
			
			for(int j = 0; j < i; j++) {
				sr += dr[d];
				sc += dc[d];
				arr[sr][sc] = ++num;
			}
			d = (d+1)%4;
		}
		
		for(int j = 0; j < N-1; j++) {
			sr += dr[d];
			sc += dc[d];
			arr[sr][sc] = ++num;
		}
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				sb.append(arr[r][c]).append(" ");
				if(arr[r][c] == T) {
					ar = r+1;
					ac = c+1;
				}
			}
			sb.append("\n");
		}
		
		sb.append(String.format("%d %d\n", ar, ac));
		System.out.println(sb);
	}
}
