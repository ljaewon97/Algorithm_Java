package boj.b2;

public class B2_13458_시험_감독 {
	static Reader in = new Reader();
	
	public static void main(String[] args) throws Exception {
		long ans = 0;
		
		int N = in.nextInt();
		
		long[] arr = new long[N];
		
		for(int i = 0; i < N; i++) {
			arr[i] = in.nextLong();
		}
		
		int B = in.nextInt();
		int C = in.nextInt();
		
		for(int i = 0; i < N; i++) {
			if(arr[i] <= B) {
				ans += 1;
			}
			else {
				ans += Math.max(arr[i] - B, 0) / C + ((arr[i] - B) % C == 0 ? 1 : 2);
			}
		}
		
		System.out.println(ans);
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
