package boj.s3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S3_01002_터렛 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int r1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			int r2 = Integer.parseInt(st.nextToken());
			
			if(x1 == x2 && y1 == y2 && r1 == r2) {
				sb.append(-1).append("\n");
				continue;
			}
			
			double d = calcDist(x1, y1, x2, y2);
			
			if(d > r1 + r2) {
				sb.append(0).append("\n");
			}
			else if(d == r1 + r2) {
				sb.append(1).append("\n");
			}
			else {
				int maxr = Math.max(r1, r2);
				int minr = Math.min(r1, r2);
				
				if(d + minr > maxr) {
					sb.append(2).append("\n");
				}
				else if(d + minr == maxr) {
					sb.append(1).append("\n");
				}
				else {
					sb.append(0).append("\n");
				}
			}
		}
		
		System.out.println(sb);
	}
	
	static double calcDist(int x1, int y1, int x2, int y2) {
		return Math.sqrt((x2-x1) * (x2-x1) + (y2-y1) * (y2-y1));
	}
}
