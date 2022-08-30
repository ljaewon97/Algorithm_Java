package boj.g2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G2_17387_선분_교차_2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int x1 = Integer.parseInt(st.nextToken());
		int y1 = Integer.parseInt(st.nextToken());
		int x2 = Integer.parseInt(st.nextToken());
		int y2 = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int x3 = Integer.parseInt(st.nextToken());
		int y3 = Integer.parseInt(st.nextToken());
		int x4 = Integer.parseInt(st.nextToken());
		int y4 = Integer.parseInt(st.nextToken());
		
		long a1 = y2 - y1;
		long b1 = x1 - x2;
		long c1 = a1 * x1 + b1 * y1;
		
		long a2 = y4 - y3;
		long b2 = x3 - x4;
		long c2 = a2 * x3 + b2 * y3;
		
		long det = a1 * b2 - b1 * a2;
		
		if(det == 0) {
			if(b1 == 0 && b2 == 0 && c1/a1 != c2/a2) {
				System.out.println(0);
				return;
			}
			
			if(b1 != 0 && b2 != 0 && c1/b1 != c2/b2) {
				System.out.println(0);
				return;
			}
			
			if((x1 == x3 && y1 == y3 && x2 == x4 && y2 == y4) || (x1 == x4 && y1 == y4 && x2 == x3 && y2 == y3)) {
				System.out.println(1);
				return;
			}
			
			int sx = Math.min(x1, x2);
			int bx = Math.max(x1, x2);
			int sy = Math.min(y1, y2);
			int by = Math.max(y1, y2);
			
			if((sx < x3 && x3 < bx) || (sx < x4 && x4 < bx)) {
				System.out.println(1);
				return;
			}
			
			if((sy < y3 && y3 < by) || (sy < y4 && y4 < by)) {
				System.out.println(1);
				return;
			}
			
			sx = Math.min(x3, x4);
			bx = Math.max(x3, x4);
			sy = Math.min(y3, y4);
			by = Math.max(y3, y4);
			
			if((sx < x1 && x1 < bx) || (sx < x2 && x2 < bx)) {
				System.out.println(1);
				return;
			}
			
			if((sy < y1 && y1 < by) || (sy < y2 && y2 < by)) {
				System.out.println(1);
				return;
			}
			
			if((x1 == x3 && y1 == y3) || (x1 == x4 && y1 == y4)) {
				System.out.println(1);
				return;
			}
			
			if((x2 == x3 && y2 == y3) || (x2 == x4 && y2 == y4)) {
				System.out.println(1);
				return;
			}
			
			System.out.println(0);
			return;
		}
		
		int d123 = CCW(x1, y1, x2, y2, x3, y3);
		int d124 = CCW(x1, y1, x2, y2, x4, y4);
		int d341 = CCW(x3, y3, x4, y4, x1, y1);
		int d342 = CCW(x3, y3, x4, y4, x2, y2);
		
		int v12 = d123 * d124;
		int v34 = d341 * d342;
		
		System.out.println(v12 <= 0 && v34 <= 0 ? 1 : 0);
	}
	
	static int CCW(int x1, int y1, int x2, int y2, int x3, int y3) {
		long value = (long) (x2 - x1) * (y3 - y1) - (long) (x3 - x1) * (y2 - y1);
		return value < 0 ? -1 : value > 0 ? 1 : 0;
	}
}
