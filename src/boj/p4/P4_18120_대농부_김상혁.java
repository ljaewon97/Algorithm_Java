package boj.p4;

import java.util.Arrays;
import java.util.Comparator;

public class P4_18120_대농부_김상혁 {
	static Reader in = new Reader();
	static double[][] crops;
	static int N, A;
	static double ans;
	
	public static void main(String[] args) throws Exception {
		N = in.nextInt();
		A = in.nextInt();
		
		crops = new double[N][2];
		double b = 0;
		double c = 0;
		
		for(int i = 0; i < N; i++) {
			int x = in.nextInt();
			int y = in.nextInt();
			int w = in.nextInt();
			int r2 = x * x + y * y;
			
			crops[i][0] = Math.sqrt(r2);
			crops[i][1] = w;
			b += w;
			c += crops[i][0] * w;
		}
		
		Arrays.sort(crops, new Comparator<double[]>() {
			public int compare(double[] o1, double[] o2) {
				return Double.compare(o1[0], o2[0]);
			}
		});
		
		ans = b * b / (4 * A) - c;
		
		double r = 0;
		b = 0;
		c = 0;
		
		for(int i = 0; i < N-1; i++) {
			double mid = b / (2 * A);
			
			if(r <= mid && mid <= crops[i][0]) {
				ans = Math.max(ans, -A * mid * mid + b * mid - c);
			}
			
			r = crops[i][0];
			ans = Math.max(ans, -A * r * r + b * r - c);
			
			b += crops[i][1];
			c += r * crops[i][1];
			ans = Math.max(ans, -A * r * r + b * r - c);
		}
		
		System.out.println(ans < 0 ? 0 : ans);
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
			if (neg)
				return -n;
			return n;
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
