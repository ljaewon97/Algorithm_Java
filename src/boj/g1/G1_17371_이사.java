package boj.g1;

public class G1_17371_이사 {
	static Reader in = new Reader();
	
	public static void main(String[] args) throws Exception {
		double min = Double.MAX_VALUE;
		int idx = -1;
		
		int N = in.nextInt();
		
		Point[] points = new Point[N];
		
		for(int i = 0; i < N; i++) {
			points[i] = new Point(in.nextInt(), in.nextInt());
		}
		
		for(int i = 0; i < N; i++) {
			Point p1 = points[i];
			double max = 0;
			
			for(int j = 0; j < N; j++) {
				Point p2 = points[j];
				max = Math.max(max, calcDist(p1, p2));
			}
			
			if(max < min) {
				min = max;
				idx = i;
			}
		}
		
		System.out.println(points[idx].x + " " + points[idx].y);
	}
	
	static double calcDist(Point a, Point b) {
		return Math.sqrt((a.x-b.x)*(a.x-b.x)+(a.y-b.y)*(a.y-b.y));
	}
	
	static class Point {
		int x, y;
		
		public Point(int x, int y) {
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
			while ((c = read()) <= 32);
			boolean neg = c == '-' ? true : false;
			if (neg)
				c = read();
			do n = (n << 3) + (n << 1) + (c & 15);
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
