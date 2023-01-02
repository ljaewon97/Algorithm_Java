package boj.g2;

public class G2_01007_벡터_매칭 {
	static Reader in = new Reader();
	static Point[] points;
	static int[] result;
	static double ans;
	static int N, xsum, ysum;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();

		int T = in.nextInt();
		
		while(T-- > 0) {
			xsum = 0;
			ysum = 0;
			N = in.nextInt();
			
			points = new Point[N];
			
			for(int i = 0; i < N; i++) {
				int x = in.nextInt();
				int y = in.nextInt();
				points[i] = new Point(x, y);
				xsum += x;
				ysum += y;
			}
			
			result = new int[N/2];
			ans = Double.MAX_VALUE;
			
			comb(0, 0);
			
			sb.append(ans).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static void comb(int nth, int start) {
		if(nth == N/2) {
			int xtemp = 0, ytemp = 0;
			
			for(int i = 0; i < N/2; i++) {
				xtemp += 2*points[result[i]].x;
				ytemp += 2*points[result[i]].y;
			}
			
			int x = xsum - xtemp;
			int y = ysum - ytemp;
			
			ans = Math.min(ans, calc(x, y));
			return;
		}
		
		for(int i = start; i < N; i++) {
			result[nth] = i;
			comb(nth+1, i+1);
		}
	}
	
	static double calc(int x, int y) {
		return Math.sqrt((long)x*x + (long)y*y);
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
