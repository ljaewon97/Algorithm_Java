package boj.p4;

import java.util.Arrays;
import java.util.Comparator;

public class P4_03679_단순_다각형 {
	static Reader in = new Reader();
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		int c = in.nextInt();
		
		while(c-- > 0) {
			int n = in.nextInt();
			
			Point[] points = new Point[n];
			
			for(int i = 0; i < n; i++) {
				long x = in.nextLong();
				long y = in.nextLong();
				points[i] = new Point(i, x, y);
			}
			
			Arrays.sort(points, new Comparator<Point>() {
				public int compare(Point o1, Point o2) {
					if(o1.x != o2.x) return Long.compare(o1.x, o2.x);
					return Long.compare(o1.y, o2.y);
				}
			});
			
			Arrays.sort(points, new Comparator<Point>() {
				public int compare(Point o1, Point o2) {
					int cw = ccw(points[0], o1, o2);
					if(cw == 0) return Long.compare(dist(points[0], o1), dist(points[0], o2));
					return Integer.compare(0, cw);
				}
			});
			
			int idx = n-1;
			
			for(int i = n-1; i >= 1; i--) {
				if(ccw(points[0], points[idx-1], points[idx]) == 0) idx--;
				else break;
			}
			
			for(int i = 0; i < idx; i++) {
				sb.append(points[i].no).append(" ");
			}
			
			for(int i = n-1; i >= idx; i--) {
				sb.append(points[i].no).append(" ");
			}
			
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	static long dist(Point a, Point b) {
		return (a.x-b.x)*(a.x-b.x) + (a.y-b.y)*(a.y-b.y);
	}
	
	static int ccw(Point a, Point b, Point c) {
		long res = a.x*b.y + b.x*c.y + c.x*a.y - b.x*a.y - c.x*b.y - a.x*c.y;
		return res > 0 ? 1 : (res == 0 ? 0 : -1);
	}
	
	static class Point {
		int no;
		long x, y;
		
		public Point(int no, long x, long y) {
			this.no = no;
			this.x = x;
			this.y = y;
		}
	}
	
	static class Reader {
		final int SIZE = 1 << 13;
		byte[] buffer = new byte[SIZE];
		int index, size;

		int nextInt() throws Exception {
			int n = 0;
			byte c;
			while ((c = read()) <= 32)
				;
			boolean neg = c == '-' ? true : false;
			if (neg)
				c = read();
			do
				n = (n << 3) + (n << 1) + (c & 15);
			while (isNumber(c = read()));
			return neg ? -n : n;
		}

		long nextLong() throws Exception {
			long n = 0;
			byte c;
			while ((c = read()) <= 32)
				;
			boolean neg = c == '-' ? true : false;
			if (neg)
				c = read();
			do
				n = (n << 3) + (n << 1) + (c & 15);
			while (isNumber(c = read()));
			return neg ? -n : n;
		}

		boolean isNumber(byte c) {
			return 47 < c && c < 58;
		}

		byte read() throws Exception {
			if (index == size) {
				size = System.in.read(buffer, index = 0, SIZE);
				if (size < 0)
					buffer[0] = -1;
			}
			return buffer[index++];
		}
	}
}
