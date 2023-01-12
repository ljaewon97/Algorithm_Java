package boj.s3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class S3_01485_정사각형 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			int[][] points = new int[4][2];
			
			for(int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine());
				points[i][0] = Integer.parseInt(st.nextToken());
				points[i][1] = Integer.parseInt(st.nextToken());
			}
			
			long[] dist = new long[6];
			int idx = 0;
			
			for(int i = 0; i < 4; i++) {
				for(int j = i+1; j < 4; j++) {
					dist[idx++] = calcDist(points, i, j);
				}
			}
			
			Arrays.sort(dist);
			
			boolean ans = dist[0] == dist[1] && dist[1] == dist[2] && dist[2] == dist[3] && dist[4] == dist[5];
			
			sb.append(ans ? "1\n" : "0\n");
		}
		
		System.out.println(sb);
	}
	
	static long calcDist(int[][] points, int i, int j) {
		long dx = points[i][0] - points[j][0];
		long dy = points[i][1] - points[j][1];
		return dx*dx + dy*dy;
	}
}
