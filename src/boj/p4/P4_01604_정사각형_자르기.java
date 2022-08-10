package boj.p4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P4_01604_정사각형_자르기 {
	static int[][] lines;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int valid = 0;
		
		lines = new int[N][];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			if(x1 <= -10 && x2 <= -10) continue;
			if(x1 >= 10 && x2 >= 10) continue;
			if(y1 <= -10 && y2 <= -10) continue;
			if(y1 >= 10 && y2 >= 10) continue;
			
			int a = y2 - y1;
			int b = x1 - x2;
			int c = a * x1 + b * y1;
			
			lines[valid++] = new int[] {a, b, c};
		}
		
		if(valid == 0) {
			System.out.println(1);
			return;
		}
		
		int ans = 1;
		
		if(onSquare(0)) {
			ans++;
		}
		
		for(int i = 1; i < valid; i++) {
			int crossPoint = 0;
			
			for(int j = i-1; j >= 0; j--) {
				crossPoint += checkCross(i, j);
			}
			
			if(crossPoint != 0) {
				ans += crossPoint + 1;
			}
			else {
				if(onSquare(i)) {
					ans += 1;
				}
			}
		}

		System.out.println(ans);
	}
	
	static boolean onSquare(int ln) {
		int a = lines[ln][0];
		int b = lines[ln][1];
		int c = lines[ln][2];
		
		// (10, 10)
		if(a * 10 + b * 10 == c) {
			double y = 1.0 * (c + 10 * a) / b;
			if(-10 <= y && y < 10) return true;
			
			double x = 1.0 * (c + 10 * b) / a;
			if(-10 <= x && x < 10) return true;
		}
		
		// (10, -10)
		if(a * 10 + b * -10 == c) {
			double y = 1.0 * (c + 10 * a) / b;
			if(-10 < y && y <= 10) return true;
			
			double x = 1.0 * (c - 10 * b) / a;
			if(-10 <= x && x < 10) return true;
		}
		
		// (-10, -10)
		if(a * -10 + b * -10 == c) {
			double x = 1.0 * (c - 10 * b) / a;
			if(-10 < x && x <= 10) return true;
			
			double y = 1.0 * (c - 10 * a) / b;
			if(-10 < y && y <= 10) return true;
		}
		
		// (-10, 10)
		if(a * -10 + b * 10 == c) {
			double y = 1.0 * (c - 10 * a) / b;
			if(-10 <= y && y < 10) return true;
			
			double x = 1.0 * (c + 10 * b) / a;
			if(-10 < x && x <= 10) return true;
		}
		
		if(b != 0) {
			// x = -10
			double y = 1.0 * (c + 10 * a) / b;
			if(-10 < y && y < 10) return true;
			
			// x = 10
			y = 1.0 * (c - 10 * a) / b;
			if(-10 < y && y < 10) return true;
		}
		
		if(a != 0) {
			// y = -10
			double x = 1.0 * (c + 10 * b) / a;
			if(-10 < x && x < 10) return true;
			
			// y = 10
			x = 1.0 * (c - 10 * b) / a;
			if(-10 < x && x < 10) return true;
		}
		
		return false;
	}
	
	static int checkCross(int ln1, int ln2) {
		int a1 = lines[ln1][0];
		int b1 = lines[ln1][1];
		int c1 = lines[ln1][2];
		
		int a2 = lines[ln2][0];
		int b2 = lines[ln2][1];
		int c2 = lines[ln2][2];
		
		int det = a1 * b2 - b1 * a2;
		
		if(det == 0) {
			return 0;
		}
		
		double x = 1.0 * (c1 * b2 - b1 * c2) / det;
		double y = 1.0 * (a1 * c2 - c1 * a2) / det;
		
		if(-10 < x && x < 10 && -10 < y && y < 10) {
			return 1;
		}
		return 0;
	}
}
