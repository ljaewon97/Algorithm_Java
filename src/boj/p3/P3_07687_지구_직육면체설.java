package boj.p3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P3_07687_지구_직육면체설 {
	static int a, b, c, x, y, z;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			z = Integer.parseInt(st.nextToken());
			
			if(a == 0 && b == 0 && c == 0) break;
			
			int minDist = calcMinDist(a, b, c, x, y, z);
			
			sb.append(minDist).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static int calcMinDist(int a, int b, int c, int x, int y, int z) {
		if(x == 0 || y == 0 || z == 0) {
			return x * x + y * y + z * z;
		}
		
		int min = 1000000000 + 1;
		if(x == a) {
			int dist1 = (a + y) * (a + y) + z * z;
			int dist2 = (a + z) * (a + z) + y * y;
			int dist3 = (a + c - z) * (a + c - z) + (c + y) * (c + y);
			int dist4 = (a + b - y) * (a + b - y) + (b + z) * (b + z);
			
			min = Math.min(min, Math.min(Math.min(Math.min(dist1, dist2), dist3), dist4));
		}
		if(y == b) {
			int dist1 = (b + x) * (b + x) + z * z;
			int dist2 = (b + z) * (b + z) + x * x;
			int dist3 = (b + c - z) * (b + c - z) + (c + x) * (c + x);
			int dist4 = (a + z) * (a + z) + (b + a - x) * (b + a - x); 
			
			min = Math.min(min, Math.min(Math.min(Math.min(dist1, dist2), dist3), dist4));
		}
		if(z == c) {
			int dist1 = (c + x) * (c + x) + y * y;
			int dist2 = (c + y) * (c + y) + x * x;
			int dist3 = (a + y) * (a + y) + (c + a - x) * (c + a - x);
			int dist4 = (b + x) * (b + x) + (c + b - y) * (c + b - y);
			
			min = Math.min(min, Math.min(Math.min(Math.min(dist1, dist2), dist3), dist4));
		}
		
		return min;
	}
}
