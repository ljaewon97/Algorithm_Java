package boj.g5;

public class G5_02166_다각형의_면적 {
	static Reader in = new Reader();
	
	public static void main(String[] args) throws Exception {
		int N = in.nextInt();
		
		double sum = 0.0;
		
		int x1 = in.nextInt();
		int y1 = in.nextInt();
		int xprev = x1;
		int yprev = y1;
		
		for(int i = 0; i < N-1; i++) {
			int x = in.nextInt();
			int y = in.nextInt();
			
			sum += 1.0 * xprev * y - 1.0 * x * yprev;
			
			xprev = x;
			yprev = y;
		}
		
		sum += 1.0 * xprev * y1 - 1.0 * x1 * yprev;
		
		System.out.printf("%.1f\n", Math.abs(sum) / 2);
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
