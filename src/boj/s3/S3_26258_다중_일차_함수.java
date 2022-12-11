package boj.s3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S3_26258_다중_일차_함수 {
	static int[][] points;
	static int N, Q;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		points = new int[N][2];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			points[i][0] = Integer.parseInt(st.nextToken());
			points[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Q = Integer.parseInt(br.readLine());
		
		while(Q-- > 0) {
			double k = Double.parseDouble(br.readLine());
			int i = lowerBound(k);
			
			if(points[i-1][1] < points[i][1]) sb.append("1");
			else if(points[i-1][1] == points[i][1]) sb.append("0");
			else sb.append("-1");
			
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	static int lowerBound(double target) {
		int l = 0;
		int r = N;
		
		while(l < r) {
			int m = (l + r) / 2;
			
			if((double) points[m][0] >= target) r = m;
			else l = m + 1;
		}
		
		return r;
	}
}
