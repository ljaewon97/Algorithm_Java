package boj.s3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S3_01002_터렛_2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int r1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			int r2 = Integer.parseInt(st.nextToken());
			
			if(x1 == x2 && y1 == y2 && r1 == r2) {
				sb.append("-1\n");
				continue;
			}
			
			int d = (x1-x2)*(x1-x2) + (y1-y2)*(y1-y2);
			int rr = (r1+r2)*(r1+r2);
			
			if(d > rr) {
				sb.append("0\n");
			}
			else if(d == rr) {
				sb.append("1\n");
			}
			else {
				int minr = Math.min(r1, r2);
				int maxr = Math.max(r1, r2);
				int rdiff = (maxr-minr)*(maxr-minr);
				
				if(d > rdiff) sb.append("2\n");
				else if(d == rdiff) sb.append("1\n");
				else sb.append("0\n");
			}
		}
		
		System.out.println(sb);
	}
}
